package com.mozipp.product.users;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetShopDto {
    private String petShopName;
    private String address;
    private String addressDetail;

    @Builder
    public PetShopDto(String petShopName, String address, String addressDetail) {
        this.petShopName = petShopName;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}