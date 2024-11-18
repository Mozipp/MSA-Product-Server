package com.mozipp.product.users;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetGroomingImageDto {
    private String imageUrl;

    @Builder
    public PetGroomingImageDto(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
