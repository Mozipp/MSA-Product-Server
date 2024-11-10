package com.mozipp.product.domain.reservation.dto;

import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.test.designer.dto.PetShopDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelReservationListDto {
    private Long reservationId;
    private PetShopDto petShop;
    private String design;
    private ReservationStatus reservationStatus;
    private LocalDateTime reservationRequestDate;
    private LocalDateTime createdAt;

    @Builder
    public ModelReservationListDto(Long reservationId, PetShopDto petShop, String design, ReservationStatus reservationStatus, LocalDateTime reservationRequestDate, LocalDateTime createdAt) {
        this.reservationId = reservationId;
        this.petShop = petShop;
        this.design = design;
        this.reservationStatus = reservationStatus;
        this.reservationRequestDate = reservationRequestDate;
        this.createdAt = createdAt;
    }
}
