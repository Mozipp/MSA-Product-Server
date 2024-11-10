package com.mozipp.product.domain.request.dto;

import com.mozipp.product.domain.request.entity.RequestStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelRequestListDto {
    private Long reservationRequestId;
    private RequestStatus reservationRequestStatus;
    private String modelDescription;
    private LocalDateTime reservationRequestDate;
    private LocalDateTime createdAt;

    @Builder
    public ModelRequestListDto(Long reservationRequestId, RequestStatus reservationRequestStatus, String modelDescription, LocalDateTime reservationRequestDate, LocalDateTime createdAt) {
        this.reservationRequestId = reservationRequestId;
        this.reservationRequestStatus = reservationRequestStatus;
        this.modelDescription = modelDescription;
        this.reservationRequestDate = reservationRequestDate;
        this.createdAt = createdAt;
    }
}
