package com.mozipp.product.users;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "designer_id")
public class Designer extends User {

    private String licenseImageUrl;

    private String career;

    private Boolean isVerified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_shop_id")
    private PetShop petShop;

    @OneToMany(mappedBy = "designer")
    private List<PetGroomingImage> petGroomingImages = new ArrayList<>();

    @OneToMany(mappedBy = "designer")
    private List<DesignerProduct> products = new ArrayList<>();

    public void addDesignerProduct(DesignerProduct product) {
        products.add(product);
    }
}

