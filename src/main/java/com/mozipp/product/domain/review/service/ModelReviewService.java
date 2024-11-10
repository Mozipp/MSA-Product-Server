package com.mozipp.product.domain.review.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.review.converter.ReviewConverter;
import com.mozipp.product.domain.review.dto.ModelReviewCreateDto;
import com.mozipp.product.domain.review.entity.Review;
import com.mozipp.product.domain.review.repository.ReviewRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelReviewService {

    private final ReviewRepository reviewRepository;
    private final DesignerProductRepository designerProductRepository;

    public List<ReviewDto> getReviewsForModel(Long modelId) {
        List<Review> reviews = reviewRepository.findByTargetId(modelId);
        List<ReviewDto> reviewDtos = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDto reviewDto = ReviewDto.builder()
                    .reviewId(review.getId())
                    .reviewContent(review.getReviewContent())
                    .build();
            reviewDtos.add(reviewDto);
        }

        return reviewDtos;
    }

    @Transactional
    public void createModelReview(ModelReviewCreateDto request, User user) {
        DesignerProduct designerProduct = designerProductRepository.findById(request.getDesignerProductId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));
        Review review = ReviewConverter.toModelReview(request, user, designerProduct);
        reviewRepository.save(review);
    }
}
