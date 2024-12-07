package com.mozipp.product.global.config.redis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PortfolioResultEvent {
    private final Long productId;
}
