package com.mozipp.product.domain.request.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDto {
    private Long reviewId;
    private String reviewContent;
    private LocalDateTime createdAt;

    @Builder
    public ReviewDto(Long reviewId, String reviewContent, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.createdAt = createdAt;
    }
}
