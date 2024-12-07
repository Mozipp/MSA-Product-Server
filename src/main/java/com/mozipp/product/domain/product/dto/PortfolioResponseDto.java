package com.mozipp.product.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PortfolioResponseDto {
    private Long portfolioId;
    private String naverPlaceUrl;
    private Long designerId;
}
