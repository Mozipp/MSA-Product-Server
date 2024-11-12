package com.mozipp.product.domain.reservation.dto;

import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerReservationHandleDto {
    private ReservationStatus reservationStatus;
}
