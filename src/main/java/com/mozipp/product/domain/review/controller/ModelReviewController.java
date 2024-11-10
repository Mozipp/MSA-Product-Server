package com.mozipp.product.domain.review.controller;

import com.mozipp.product.domain.review.dto.ModelReviewCreateDto;
import com.mozipp.product.domain.review.service.ModelReviewService;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.test.user.entity.User;
import com.mozipp.product.test.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model/review")
public class ModelReviewController {

    private final ModelReviewService modelReviewService;
    private final UserFindService userFindService;

    // Model 리뷰 등록
    @PostMapping
    public BaseResponse<Object> createModelReview(@RequestBody ModelReviewCreateDto request, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        modelReviewService.createModelReview(request, user);
        return BaseResponse.success();
    }
}
