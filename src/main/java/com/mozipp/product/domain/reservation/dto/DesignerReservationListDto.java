package com.mozipp.product.domain.reservation.dto;

import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerReservationListDto {
    private Long reservationId;
    private String design;
    private ReservationListModelDto model;
    private ReservationStatus reservationStatus;
    private LocalDateTime reservationDate;
    private LocalDateTime createdAt;

    @Builder
    public DesignerReservationListDto(Long reservationId, String design, ReservationListModelDto model, ReservationStatus reservationStatus, LocalDateTime reservationDate, LocalDateTime createdAt) {
        this.reservationId = reservationId;
        this.design = design;
        this.model = model;
        this.reservationStatus = reservationStatus;
        this.reservationDate = reservationDate;
        this.createdAt = createdAt;
    }
}
