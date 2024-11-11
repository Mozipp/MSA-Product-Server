package com.mozipp.product.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.test.designer.dto.PetShopDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerProductListDto {

    private Long designerProductId;
    private String title;
    private String introduction;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
    private ProductStatus productStatus;
    private PetShopDto petShop;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    public DesignerProductListDto(Long designerProductId, String title, String introduction, String design, String modelPreferDescription, String preferBreed, ProductStatus productStatus, PetShopDto petShop, LocalDateTime createdAt) {
        this.designerProductId = designerProductId;
        this.title = title;
        this.introduction = introduction;
        this.design = design;
        this.modelPreferDescription = modelPreferDescription;
        this.preferBreed = preferBreed;
        this.productStatus = productStatus;
        this.petShop = petShop;
        this.createdAt = createdAt;
    }
}
