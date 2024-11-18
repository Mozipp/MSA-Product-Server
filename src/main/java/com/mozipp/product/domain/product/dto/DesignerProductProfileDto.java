package com.mozipp.product.domain.product.dto;

import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.test.model.entity.Gender;
import com.mozipp.product.users.PetGroomingImageDto;
import com.mozipp.product.users.PetShopDto;
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
    private String career;
    private PetShopDto petShop;
    private List<PetGroomingImageDto> petGroomingImageUrl;
    private List<ReviewDto> reviews;

    @Builder
    public DesignerProductProfileDto(String name, Gender gender, String career, PetShopDto petShop, List<PetGroomingImageDto> petGroomingImageUrl, List<ReviewDto> reviews) {
        this.name = name;
        this.gender = gender;
        this.career = career;
        this.petShop = petShop;
        this.petGroomingImageUrl = petGroomingImageUrl;
        this.reviews = reviews;
    }
}
