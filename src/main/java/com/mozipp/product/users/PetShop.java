package com.mozipp.product.users;

import com.mozipp.product.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetShop extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String petShopName;

    private String address;

    private String addressDetail;

    @OneToMany(mappedBy = "petShop")
    private List<Designer> designers = new ArrayList<>();

    @Builder
    public PetShop(String petShopName, String address, String addressDetail) {
        this.petShopName = petShopName;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
