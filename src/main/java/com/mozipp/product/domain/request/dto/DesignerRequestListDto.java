package com.mozipp.product.domain.request.dto;

import com.mozipp.product.domain.request.entity.RequestStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerRequestListDto {
    private Long reservationRequestId;
    private RequestStatus reservationRequestStatus;
    private ReservationRequestModelDto model;
    private LocalDateTime reservationRequestDate;
    private LocalDateTime createdAt;

    @Builder
    public DesignerRequestListDto(Long reservationRequestId, RequestStatus reservationRequestStatus, ReservationRequestModelDto model, LocalDateTime reservationRequestDate, LocalDateTime createdAt) {
        this.reservationRequestId = reservationRequestId;
        this.reservationRequestStatus = reservationRequestStatus;
        this.model = model;
        this.reservationRequestDate = reservationRequestDate;
        this.createdAt = createdAt;
    }
}
