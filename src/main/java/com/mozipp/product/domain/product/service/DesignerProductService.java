package com.mozipp.product.domain.product.service;

import com.mozipp.product.domain.product.converter.DesignerProductConverter;
import com.mozipp.product.domain.product.dto.*;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.product.entity.TransactionStatus;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.review.service.DesignerReviewService;
import com.mozipp.product.global.config.redis.PortfolioCreationEvent;
import com.mozipp.product.global.config.redis.RedisEventPublisher;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.global.util.CookieUtil;
import com.mozipp.product.users.PetShop;
import com.mozipp.product.users.Designer;
import com.mozipp.product.users.PetGroomingImage;
import com.mozipp.product.users.PetShopDto;
import com.mozipp.product.users.repository.DesignerRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerProductService {

    private final DesignerProductRepository designerProductRepository;
    private final DesignerReviewService designerReviewService;
    private final DesignerRepository designerRepository;
    private final WebClient webClient;
    private final CookieUtil cookieUtil;
    private final RedisEventPublisher redisEventPublisher;

    private static final Logger logger = LoggerFactory.getLogger(DesignerProductService.class);

    @Value("${user.service.url}")
    private String userServiceUrl;

    public List<DesignerProductListDto> getDesignerProducts(ProductStatus status) {
        List<DesignerProduct> designerProducts = designerProductRepository.findByProductStatus(status);
        return DesignerProductConverter.toDesignerProductListDto(designerProducts);
    }

    @Transactional
    public void createDesignerProduct(DesignerProductCreateDto request, Long designerId) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        DesignerProduct designerProduct = DesignerProductConverter.toDesignerProduct(request);
        designerProduct.updateDesigner(designer);
        designerProductRepository.save(designerProduct);
    }

    public List<DesignerProductListDto> getMyDesignerProducts(Long designerId, ProductStatus status) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));
        List<DesignerProduct> myDesignerProducts = designerProductRepository.findByDesignerAndProductStatus(designer, status);
        return DesignerProductConverter.toDesignerProductListDto(myDesignerProducts);
    }

    public DesignerProductProfileDto getModelToDesignerProfile(Long designerProductId) {
        DesignerProduct designerProduct = designerProductRepository.findById(designerProductId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));

        Designer designer = designerProduct.getDesigner();
        PetShop petShop = designer.getPetShop();

        PetShopDto petShopDto = DesignerProductConverter.toPetShopDto(petShop);

        List<PetGroomingImage> petGroomingImages = designer.getPetGroomingImages();
        List<ReviewDto> reviews = designerReviewService.getReviewsForDesigner(designer.getId());

        return DesignerProductConverter.toDesignerProductProfileDto(
                designerProduct,
                petShopDto,
                petGroomingImages,
                reviews
        );

    }

    @Transactional
    public void createDesignerProductAndPortfolio(HttpServletRequest httpRequest, DesignerProductPortfolioDto request, Long designerId) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));
        DesignerProduct designerProduct = DesignerProductConverter.toDesignerProductPortfolio(request);
        designerProduct.updateDesigner(designer);
        designerProductRepository.save(designerProduct);

        // 2. Redis 이벤트 발행 (User 서버가 소비하여 Portfolio 생성 시도)
        PortfolioCreationEvent event = new PortfolioCreationEvent(designerId, request.getNaverPlaceUrl(), designerProduct.getId());
        redisEventPublisher.publishPortfolioCreationRequest(event);


        // User 서버와 통신하여 naverPlaceUrl 정보를 Portfolio Entity에 저장
        String naverPlaceUrl = request.getNaverPlaceUrl();
        // 쿠키에서 access_token 추출
        String accessToken = cookieUtil.getCookieValue(httpRequest, "access_token");

        if (accessToken == null) {
            throw new BaseException(BaseResponseStatus.UNAUTHORIZED); // 적절한 예외 처리
        }

        webClient.post()
                .uri(userServiceUrl + "/api/users/portfolios")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .bodyValue(new PortfolioRequest(designerId, naverPlaceUrl))
                .retrieve()
                .bodyToMono(PortfolioResponseDto.class)
                .doOnSuccess(response -> logger.info("Portfolio created successfully on User server: portfolioId={}", response.getPortfolioId()))
                .doOnError(error -> {
                    logger.info("portfolio create fail");
                    throw new BaseException(BaseResponseStatus.PORTFOLIO_CREATION_FAILED);
                })
                .block(); // 블로킹 호출
    }

    @Transactional
    public void handlePortfolioCreated(Long productId) {
        DesignerProduct product = designerProductRepository.findById(productId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT));
        product.updateTransactionStatus(TransactionStatus.CONFIRMED);
        logger.info("Product with ID {} has been created.", productId);
    }

    @Transactional
    public void handlePortfolioCreationFailed(Long productId) {
        DesignerProduct product = designerProductRepository.findById(productId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT));
        product.updateTransactionStatus(TransactionStatus.FAILED);
        designerProductRepository.delete(product);
        logger.info("Product with ID {} has been deleted after portfolio creation failure.", productId);
    }
}
