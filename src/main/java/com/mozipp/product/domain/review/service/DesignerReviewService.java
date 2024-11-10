package com.mozipp.product.domain.review.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.request.service.FindModelService;
import com.mozipp.product.domain.review.converter.ReviewConverter;
import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.entity.Review;
import com.mozipp.product.domain.review.repository.ReviewRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DesignerReviewService {

    private final ReviewRepository reviewRepository;
    private final DesignerProductRepository designerProductRepository;
    private final FindModelService findModelService;

    @Transactional
    public void createDesignerReview(DesignerReviewCreateDto request, User user) {
        DesignerProduct designerProduct = designerProductRepository.findById(request.getDesignerProductId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));

        Long modelId = findModelService.getDesignerProductIdForModel(request.getDesignerProductId());

        Review review = ReviewConverter.toDesignerReview(request, user, designerProduct, modelId);
        reviewRepository.save(review);
    }
}
