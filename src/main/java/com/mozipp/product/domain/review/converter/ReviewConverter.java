package com.mozipp.product.domain.review.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.dto.ModelReviewCreateDto;
import com.mozipp.product.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toDesignerReview(DesignerReviewCreateDto request, Long designerId, DesignerProduct designerProduct, Long modelId) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .userId(designerId)
                .targetId(modelId)
                .designerProduct(designerProduct)
                .build();
    }

    public static Review toModelReview(ModelReviewCreateDto request, Long modelId, DesignerProduct designerProduct, Long designerId) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .userId(modelId)
                .targetId(designerId)
                .designerProduct(designerProduct)
                .build();
    }
}
