package com.mozipp.product.domain.request.entity;

import lombok.Getter;

@Getter
public enum RequestStatus {
    PENDING, ACCEPTED, REJECTED, CANCELED
}
