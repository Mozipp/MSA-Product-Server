package com.mozipp.product.domain.review.controller;

import com.mozipp.product.domain.review.dto.DesignerReviewCreateDto;
import com.mozipp.product.domain.review.service.DesignerReviewService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.designer.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/review")
public class DesignerReviewController {

    private final DesignerReviewService designerReviewService;
    private final DesignerRepository designerRepository;

    // Designer 리뷰 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReview(@RequestBody DesignerReviewCreateDto request){
        Designer designer = designerRepository.findById(request.getDesignerId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND));
        designerReviewService.createDesignerReview(request, designer);
        return BaseResponse.success();
    }
}
