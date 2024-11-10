package com.mozipp.product.domain.review.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toDesignerReview(DesignerReviewCreateDto request, DesignerProduct designerProduct) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .reviewee(designerProduct.getDesigner().getId())
                .designerProduct(designerProduct)
                .build();
    }
}
