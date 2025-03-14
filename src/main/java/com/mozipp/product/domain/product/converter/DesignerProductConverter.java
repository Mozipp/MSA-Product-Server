package com.mozipp.product.domain.product.converter;

import com.mozipp.product.domain.product.dto.DesignerProductCreateDto;
import com.mozipp.product.domain.product.dto.DesignerProductListDto;
import com.mozipp.product.domain.product.dto.DesignerProductPortfolioDto;
import com.mozipp.product.domain.product.dto.DesignerProductProfileDto;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.product.entity.TransactionStatus;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.users.PetGroomingImage;
import com.mozipp.product.users.PetGroomingImageDto;
import com.mozipp.product.users.PetShop;
import com.mozipp.product.users.PetShopDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DesignerProductConverter {

    public static List<DesignerProductListDto> toDesignerProductListDto(List<DesignerProduct> designerProducts) {
        List<DesignerProductListDto> productListDtos = new ArrayList<>();
        for(DesignerProduct designerProduct : designerProducts) {
            PetShop petShop = designerProduct.getDesigner().getPetShop();
            PetShopDto petShopDto = PetShopDto.builder()
                    .petShopName(petShop.getPetShopName())
                    .address(petShop.getAddress())
                    .addressDetail(petShop.getAddressDetail())
                    .build();

            DesignerProductListDto productListDto = DesignerProductListDto.builder()
                    .designerProductId(designerProduct.getId())
                    .title(designerProduct.getTitle())
                    .introduction(designerProduct.getIntroduction())
                    .design(designerProduct.getDesign())
                    .modelPreferDescription(designerProduct.getModelPreferDescription())
                    .preferBreed(designerProduct.getPreferBreed())
                    .productStatus(designerProduct.getProductStatus())
                    .petShop(petShopDto)
                    .createdAt(designerProduct.getCreatedAt())
                    .build();
            productListDtos.add(productListDto);
        }
        return productListDtos;
    }

    public static DesignerProduct toDesignerProduct(DesignerProductCreateDto request) {
        return DesignerProduct.builder()
                .title(request.getTitle())
                .introduction(request.getIntroduction())
                .design(request.getDesign())
                .modelPreferDescription(request.getModelPreferDescription())
                .preferBreed(request.getPreferBreed())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    private static List<PetGroomingImageDto> toPetGroomingImageDtos(List<PetGroomingImage> petGroomingImages) {
        return petGroomingImages.stream()
                .map(image -> PetGroomingImageDto.builder()
                        .imageUrl(image.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public static DesignerProductProfileDto toDesignerProductProfileDto(DesignerProduct designerProduct, PetShopDto petShopDto, List<PetGroomingImage> petGroomingImages, List<ReviewDto> reviews) {

        List<PetGroomingImageDto> petGroomingImageDtos = toPetGroomingImageDtos(petGroomingImages);
        return DesignerProductProfileDto.builder()
                .name(designerProduct.getDesigner().getName())
                .gender(designerProduct.getDesigner().getGender())
                .career(designerProduct.getDesigner().getCareer())
                .petShop(petShopDto)
                .petGroomingImageUrl(petGroomingImageDtos)
                .reviews(reviews)
                .build();
    }

    public static PetShopDto toPetShopDto(PetShop petShop) {
        return PetShopDto.builder()
                .petShopName(petShop.getPetShopName())
                .address(petShop.getAddress())
                .addressDetail(petShop.getAddressDetail())
                .build();
    }

    public static DesignerProduct toDesignerProductPortfolio(DesignerProductPortfolioDto request) {
        return DesignerProduct.builder()
                .title(request.getTitle())
                .introduction(request.getIntroduction())
                .design(request.getDesign())
                .modelPreferDescription(request.getModelPreferDescription())
                .preferBreed(request.getPreferBreed())
                .productStatus(ProductStatus.AVAILABLE)
                .transactionStatus(TransactionStatus.PENDING)
                .build();
    }
}
