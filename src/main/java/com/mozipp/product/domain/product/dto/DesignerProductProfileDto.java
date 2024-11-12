package com.mozipp.product.domain.product.dto;

import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.test.designer.dto.PetShopDto;
import com.mozipp.product.test.petgroomingimage.dto.PetGroomingImageDto;
import com.mozipp.product.test.user.entity.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerProductProfileDto {
    private String name;
    private Gender gender;
    private PetShopDto petShopDto;
    private List<PetGroomingImageDto> petGroomingImageUrl;
    private List<ReviewDto> reviews;

    @Builder
    public DesignerProductProfileDto(String name, Gender gender, PetShopDto petShopDto, List<PetGroomingImageDto> petGroomingImageUrl, List<ReviewDto> reviews) {
        this.name = name;
        this.gender = gender;
        this.petShopDto = petShopDto;
        this.petGroomingImageUrl = petGroomingImageUrl;
        this.reviews = reviews;
    }
}
