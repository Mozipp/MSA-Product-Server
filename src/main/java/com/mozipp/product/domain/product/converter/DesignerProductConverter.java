package com.mozipp.product.domain.product.converter;

import com.mozipp.product.domain.product.dto.DesignerProductCreateDto;
import com.mozipp.product.domain.product.dto.DesignerProductListDto;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.test.designer.dto.PetShopDto;
import com.mozipp.product.test.petshop.entity.PetShop;

import java.util.ArrayList;
import java.util.List;

public class DesignerProductConverter {

    public static List<DesignerProductListDto> toDesignerProductResponse(List<DesignerProduct> designerProducts) {
        List<DesignerProductListDto> designerProductListDtos = new ArrayList<>();
        for(DesignerProduct designerProduct : designerProducts) {
            PetShop petShop = designerProduct.getDesigner().getPetShop();
            PetShopDto petShopDto = PetShopDto.builder()
                    .petShopName(petShop.getPetShopName())
                    .address(petShop.getAddress())
                    .addressDetail(petShop.getAddressDetail())
                    .build();

            DesignerProductListDto dto = DesignerProductListDto.builder()
                    .designerProductId(designerProduct.getId())
                    .title(designerProduct.getTitle())
                    .introduction(designerProduct.getDescription())
                    .design(designerProduct.getDesign())
                    .modelPreferDescription(designerProduct.getModelPreferDescription())
                    .preferBreed(designerProduct.getPreferBreed())
                    .productStatus(designerProduct.getProductStatus())
                    .petShop(petShopDto)
                    .createdAt(designerProduct.getCreatedAt())
                    .build();
            designerProductListDtos.add(dto);
        }
        return designerProductListDtos;
    }

    public static DesignerProduct toDesignerProduct(DesignerProductCreateDto request) {
        return DesignerProduct.builder()
                .title(request.getTitle())
                .description(request.getIntroduction())
                .design(request.getDesign())
                .modelPreferDescription(request.getModelPreferDescription())
                .preferBreed(request.getPreferBreed())
                .build();
    }
}
