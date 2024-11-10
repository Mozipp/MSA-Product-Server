package com.mozipp.product.domain.review.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelReviewService {

    private final ReservationRequestRepository reservationRequestRepository;

    public List<ReviewDto> getReviewsForModel(Long modelId){
        List<ReservationRequest> requests = reservationRequestRepository.findByModel_Id(modelId);

        List<ReviewDto> reviewDtos = new ArrayList<>();

        for(ReservationRequest request : requests) {
            DesignerProduct designerProduct = request.getDesignerProduct();
            if(designerProduct != null) {
                for(Review review : designerProduct.getReviews()) {
                    ReviewDto reviewDto = ReviewDto.builder()
                            .reviewId(review.getId())
                            .reviewContent(review.getReviewContent())
                            .build();

                    reviewDtos.add(reviewDto);
                }
            }
        }

        return reviewDtos;
    }
}
