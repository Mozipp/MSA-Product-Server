package com.mozipp.product.domain.product.service;

import com.mozipp.product.domain.product.converter.DesignerProductConverter;
import com.mozipp.product.domain.product.dto.DesignerProductCreateDto;
import com.mozipp.product.domain.product.dto.DesignerProductListDto;
import com.mozipp.product.domain.product.dto.DesignerProductProfileDto;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.review.service.DesignerReviewService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.PetShop;
import com.mozipp.product.users.Designer;
import com.mozipp.product.users.PetGroomingImage;
import com.mozipp.product.users.PetShopDto;
import com.mozipp.product.users.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerProductService {

    private final DesignerProductRepository designerProductRepository;
    private final DesignerReviewService designerReviewService;
    private final DesignerRepository designerRepository;

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
}
