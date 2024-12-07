package com.mozipp.product.global.config.redis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PortfolioCreationEvent {
    private final Long designerId;
    private final String naverPlaceUrl;
    private final Long productId;
}
