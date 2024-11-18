package com.mozipp.product.domain.review.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.dto.ModelReviewCreateDto;
import com.mozipp.product.domain.review.entity.Review;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.model.entity.Model;

public class ReviewConverter {

    public static Review toDesignerReview(DesignerReviewCreateDto request, Designer designer, DesignerProduct designerProduct, Long modelId) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .userId(designer.getId())
                .targetId(modelId)
                .designerProduct(designerProduct)
                .build();
    }

    public static Review toModelReview(ModelReviewCreateDto request, Model model, DesignerProduct designerProduct) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .userId(model.getId())
                .targetId(designerProduct.getDesigner().getId())
                .designerProduct(designerProduct)
                .build();
    }
}
