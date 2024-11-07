package com.mozipp.product.domain.product.controller;

import com.mozipp.product.domain.product.dto.DesignerProductResponse;
import com.mozipp.product.domain.product.service.DesignerProductService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/designer-product")
public class DesignerProductController {

    private final DesignerProductService designerProductService;

    // Designer 상품 페이지 목록 조회
    @GetMapping
    public BaseResponse<List<DesignerProductResponse>> getDesignerProducts() {
        return BaseResponse.success(designerProductService.getDesignerProducts());
    }

}
