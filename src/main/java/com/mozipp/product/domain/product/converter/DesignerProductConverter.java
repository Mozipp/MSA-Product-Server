package com.mozipp.product.domain.product.converter;

import com.mozipp.product.domain.product.dto.DesignerProductRequest;
import com.mozipp.product.domain.product.dto.DesignerProductResponse;
import com.mozipp.product.domain.product.entity.DesignerProduct;

import java.util.ArrayList;
import java.util.List;

public class DesignerProductConverter {

    public static List<DesignerProductResponse> toDesignerProductResponse(List<DesignerProduct> designerProducts) {
        List<DesignerProductResponse> designerProductResponses = new ArrayList<>();
        for(DesignerProduct designerProduct : designerProducts) {
            DesignerProductResponse designerProductResponse = DesignerProductResponse.builder()
                    .designerProductId(designerProduct.getId())
                    .title(designerProduct.getTitle())
                    .description(designerProduct.getDescription())
                    .design(designerProduct.getDesign())
                    .modelPreferDescription(designerProduct.getModelPreferDescription())
                    .preferBreed(designerProduct.getPreferBreed())
                    .createdAt(designerProduct.getCreatedAt())
                    .build();
            designerProductResponses.add(designerProductResponse);
        }
        return designerProductResponses;
    }

    public static DesignerProduct toDesignerProductCreateDto(DesignerProductRequest request) {
        return DesignerProduct.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .design(request.getDesign())
                .modelPreferDescription(request.getModelPreferDescription())
                .preferBreed(request.getPreferBreed())
                .build();
    }
}
