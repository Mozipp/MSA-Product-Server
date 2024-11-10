package com.mozipp.product.domain.review.service;

import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.review.entity.Review;
import com.mozipp.product.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDto> getReviewsForModel(Long modelId) {
        List<Review> reviews = reviewRepository.findByReviewee(modelId);
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
}
