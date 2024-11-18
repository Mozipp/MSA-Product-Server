package com.mozipp.product.domain.request.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ModelRequestCreateDto {
    private Long modelId;
    private Long designerProductId;
    private String modelDescription;
    private LocalDateTime reservationRequestDate;
}
