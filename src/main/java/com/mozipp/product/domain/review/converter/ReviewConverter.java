package com.mozipp.product.domain.review.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.dto.ModelReviewCreateDto;
import com.mozipp.product.domain.review.entity.Review;
import com.mozipp.product.test.user.entity.User;

public class ReviewConverter {

    public static Review toDesignerReview(DesignerReviewCreateDto request, User user, DesignerProduct designerProduct, Long modelId) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .userId(user.getId())
                .targetId(modelId)
                .designerProduct(designerProduct)
                .build();
    }

    public static Review toModelReview(ModelReviewCreateDto request, User user, DesignerProduct designerProduct) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .userId(user.getId())
                .targetId(designerProduct.getDesigner().getId())
                .designerProduct(designerProduct)
                .build();
    }
}
