package com.mozipp.product.domain.request.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ModelReservationRequestDto {
    private Long designerProductId;
    private String modelDescription;
    private LocalDateTime reservationRequestDate;
}
