package com.mozipp.product.domain.request.dto;

import com.mozipp.product.users.PetGender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationRequestModelDto {
    private String modelDescription;
    private String petName;
    private String petAge;
    private PetGender petGender;
    private String breed;
    private String petImageUrl;
    private List<ReviewDto> reviews;

    @Builder
    public ReservationRequestModelDto(String modelDescription, String petName, String petAge, PetGender petGender, String breed, String petImageUrl, List<ReviewDto> reviews) {
        this.modelDescription = modelDescription;
        this.petName = petName;
        this.petAge = petAge;
        this.petGender = petGender;
        this.breed = breed;
        this.petImageUrl = petImageUrl;
        this.reviews = reviews;
    }
}
