package com.mozipp.product.domain.product.controller;

import com.mozipp.product.domain.product.dto.DesignerProductRequest;
import com.mozipp.product.domain.product.dto.DesignerProductResponse;
import com.mozipp.product.domain.product.service.DesignerProductService;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.test.user.entity.User;
import com.mozipp.product.test.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/designer-product")
public class DesignerProductController {

    private final DesignerProductService designerProductService;
    private final UserFindService userFindService;

    // Designer 상품 페이지 목록 조회
    @GetMapping
    public BaseResponse<List<DesignerProductResponse>> getDesignerProducts() {
        return BaseResponse.success(designerProductService.getDesignerProducts());
    }

    // Designer 상품 등록
    @PostMapping
    public BaseResponse<Object> createDesignerProduct(@RequestBody DesignerProductRequest request, @AuthenticationPrincipal UserDetails userDetails){
        User user = userFindService.findByUserDetails(userDetails);
        designerProductService.createDesignerProduct(user, request);
        return BaseResponse.success();
    }

}
