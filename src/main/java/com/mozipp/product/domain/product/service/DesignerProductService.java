package com.mozipp.product.domain.product.service;

import com.mozipp.product.domain.product.converter.DesignerProductConverter;
import com.mozipp.product.domain.product.dto.DesignerProductCreateDto;
import com.mozipp.product.domain.product.dto.DesignerProductListDto;
import com.mozipp.product.domain.product.dto.DesignerProductProfileDto;
import com.mozipp.product.domain.product.entity.DesignerProduct;
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

    public List<DesignerProductListDto> getDesignerProducts() {
        List<DesignerProduct> designerProducts = designerProductRepository.findAll();
        return DesignerProductConverter.toDesignerProductListDto(designerProducts);
    }

    @Transactional
    public void createDesignerProduct(DesignerProductCreateDto request) {
        Designer designer = designerRepository.findById(request.getDesignerId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        DesignerProduct designerProduct = DesignerProductConverter.toDesignerProduct(request);
        designerProduct.updateDesigner(designer);
        designerProductRepository.save(designerProduct);
    }

    public List<DesignerProductListDto> getMyDesignerProducts(Designer designer) {
        List<DesignerProduct> designerProducts = designer.getProducts();
        return DesignerProductConverter.toDesignerProductListDto(designerProducts);
    }

    public DesignerProductProfileDto getModelToDesignerProfile(Long designerProductId) {
        DesignerProduct designerProduct = designerProductRepository.findById(designerProductId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));

        Designer designer = designerProduct.getDesigner();
        PetShop petShop = designer.getPetShop();

        PetShopDto petShopDto = PetShopDto.builder()
                .petShopName(petShop.getPetShopName())
                .address(petShop.getAddress())
                .addressDetail(petShop.getAddressDetail())
                .build();

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
