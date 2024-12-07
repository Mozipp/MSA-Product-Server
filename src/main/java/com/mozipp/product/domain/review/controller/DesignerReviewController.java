package com.mozipp.product.domain.review.controller;

import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.service.DesignerReviewService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/review")
public class DesignerReviewController {

    private final DesignerReviewService designerReviewService;

    // Designer 리뷰 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReview(@RequestBody DesignerReviewCreateDto request, @AuthenticationPrincipal Long designerId){
        designerReviewService.createDesignerReview(request, designerId);
        return BaseResponse.success();
    }
}
