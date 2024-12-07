package com.mozipp.product.domain.review.controller;

import com.mozipp.product.domain.review.dto.ModelReviewCreateDto;
import com.mozipp.product.domain.review.service.ModelReviewService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/review")
public class ModelReviewController {

    private final ModelReviewService modelReviewService;

    // Model 리뷰 등록
    @PostMapping
    public BaseResponse<Object> createModelReview(@RequestBody ModelReviewCreateDto request, @AuthenticationPrincipal Long modelId) {
        modelReviewService.createModelReview(request, modelId);
        return BaseResponse.success();
    }
}
