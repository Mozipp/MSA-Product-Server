package com.mozipp.product.domain.reservation.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    CONFIRMED, COMPLETED, CANCELLED, NO_SHOW
}
