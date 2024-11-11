package com.mozipp.product.domain.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    public ReviewDto(Long reviewId, String reviewContent, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.createdAt = createdAt;
    }
}
