package com.mozipp.product.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelReviewCreateDto {
    private Long modelId;
    private Long designerProductId;
    private String reviewContent;
}
