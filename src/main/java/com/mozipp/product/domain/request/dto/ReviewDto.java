package com.mozipp.product.domain.request.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDto {
    private Long reviewId;
    private String reviewContent;

    @Builder
    public ReviewDto(Long reviewId, String reviewContent) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
    }
}
