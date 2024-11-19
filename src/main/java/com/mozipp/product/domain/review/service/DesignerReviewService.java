package com.mozipp.product.domain.review.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.review.converter.ReviewConverter;
import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.entity.Review;
import com.mozipp.product.domain.review.repository.ReviewRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.designer.entity.Designer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerReviewService {

    private final ReviewRepository reviewRepository;
    private final DesignerProductRepository designerProductRepository;
    private final UserFindService userFindService;

    public List<ReviewDto> getReviewsForDesigner(Long designerId) {
        List<Review> reviews = reviewRepository.findByTargetId(designerId);
        List<ReviewDto> reviewDtos = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDto reviewDto = ReviewDto.builder()
                    .reviewId(review.getId())
                    .reviewContent(review.getReviewContent())
                    .createdAt(review.getCreatedAt())
                    .build();
            reviewDtos.add(reviewDto);
        }

        return reviewDtos;
    }

    @Transactional
    public void createDesignerReview(DesignerReviewCreateDto request, Designer designer) {
        DesignerProduct designerProduct = designerProductRepository.findById(request.getDesignerProductId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));

        Long modelId = userFindService.getModelId(request.getDesignerProductId());

        Review review = ReviewConverter.toDesignerReview(request, designer, designerProduct, modelId);
        reviewRepository.save(review);
    }

}
