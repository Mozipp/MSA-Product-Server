package com.mozipp.product.domain.review.controller;

import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.service.DesignerReviewService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/review")
public class DesignerReviewController {

    private final DesignerReviewService designerReviewService;
    private final UserFindService userFindService;

    // Designer 리뷰 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReview(@RequestBody DesignerReviewCreateDto request, @AuthenticationPrincipal Long designerId){
        designerReviewService.createDesignerReview(request, designerId);
        return BaseResponse.success();
    }
}
