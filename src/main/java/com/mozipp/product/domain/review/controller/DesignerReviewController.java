package com.mozipp.product.domain.review.controller;

import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.service.DesignerReviewService;
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
@RequestMapping("/api/designer/review")
public class DesignerReviewController {

    private final UserFindService userFindService;
    private final DesignerReviewService designerReviewService;

    // Designer 리뷰 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReview(@RequestBody DesignerReviewCreateDto request, @AuthenticationPrincipal UserDetails userDetails){
        User user = userFindService.findByUserDetails(userDetails);
        designerReviewService.createDesignerReview(request, user);
        return BaseResponse.success();
    }
}
