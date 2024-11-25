package com.mozipp.product.domain.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long designerProductId;
    private String title;
    private RequestStatus reservationRequestStatus;
    private ReservationRequestModelDto model;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reservationRequestDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    public DesignerRequestListDto(Long reservationRequestId, Long designerProductId, String title, RequestStatus reservationRequestStatus, ReservationRequestModelDto model, LocalDateTime reservationRequestDate, LocalDateTime createdAt) {
        this.reservationRequestId = reservationRequestId;
        this.designerProductId = designerProductId;
        this.title = title;
        this.reservationRequestStatus = reservationRequestStatus;
        this.model = model;
        this.reservationRequestDate = reservationRequestDate;
        this.createdAt = createdAt;
    }
}
