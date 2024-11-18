package com.mozipp.product.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.users.PetShopDto;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reservationRequestDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
