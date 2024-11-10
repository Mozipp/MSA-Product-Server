package com.mozipp.product.domain.product.entity;

import lombok.Getter;

@Getter
public enum ProductStatus {
    AVAILABLE("예약 가능"), RESERVED("예약됨"), COMPLETED("미용 완료");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }
}
