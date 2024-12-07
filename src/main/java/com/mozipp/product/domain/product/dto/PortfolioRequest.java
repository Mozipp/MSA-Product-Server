package com.mozipp.product.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioRequest {
    private Long designerId;
    private String naverPlaceUrl;
}
