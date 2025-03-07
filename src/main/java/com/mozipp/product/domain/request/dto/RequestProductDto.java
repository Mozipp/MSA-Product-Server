package com.mozipp.product.domain.request.dto;

import com.mozipp.product.users.PetShopDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestProductDto {
    private Long designerProductId;
    private String title;
    private String introduction;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
    private PetShopDto petShop;

    @Builder
    public RequestProductDto(Long designerProductId, String title, String introduction, String design, String modelPreferDescription, String preferBreed, PetShopDto petShop) {
        this.designerProductId = designerProductId;
        this.title = title;
        this.introduction = introduction;
        this.design = design;
        this.modelPreferDescription = modelPreferDescription;
        this.preferBreed = preferBreed;
        this.petShop = petShop;
    }
}
