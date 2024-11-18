package com.mozipp.product.domain.review.controller;

import com.mozipp.product.domain.review.dto.ModelReviewCreateDto;
import com.mozipp.product.domain.review.service.ModelReviewService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.model.entity.Model;
import com.mozipp.product.test.model.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/review")
public class ModelReviewController {

    private final ModelReviewService modelReviewService;
    private final ModelRepository modelRepository;

    // Model 리뷰 등록
    @PostMapping
    public BaseResponse<Object> createModelReview(@RequestBody ModelReviewCreateDto request) {
        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND));
        modelReviewService.createModelReview(request, model);
        return BaseResponse.success();
    }
}
