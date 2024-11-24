package com.mozipp.product.domain.product.controller;

import com.mozipp.product.domain.product.dto.DesignerProductCreateDto;
import com.mozipp.product.domain.product.dto.DesignerProductListDto;
import com.mozipp.product.domain.product.dto.DesignerProductProfileDto;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.product.service.DesignerProductService;
import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer-product")
public class DesignerProductController {

    private final DesignerProductService designerProductService;
    private final UserFindService userFindService;

    // Designer 상품 페이지 목록 조회
    @GetMapping
    public BaseResponse<List<DesignerProductListDto>> getDesignerProducts(@RequestParam("status") ProductStatus status) {
        List<DesignerProductListDto> designerProducts = designerProductService.getDesignerProducts(status);
        return BaseResponse.success(designerProducts);
    }

    // Designer 상품 등록
    @PostMapping
    public BaseResponse<Object> createDesignerProduct(@RequestBody DesignerProductCreateDto request, @RequestHeader("Authorization") String authorizationHeader){
        Long designerId = userFindService.getUserId(authorizationHeader);
        designerProductService.createDesignerProduct(request, designerId);
        return BaseResponse.success();
    }

    // Designer 본인이 등록한 상품 페이지 목록 조회
    @GetMapping("/my-product")
    public BaseResponse<List<DesignerProductListDto>> getMyDesignerProducts(@RequestParam("status") ProductStatus status, @RequestHeader("Authorization") String authorizationHeader){
        Long designerId = userFindService.getUserId(authorizationHeader);
        List<DesignerProductListDto> myDesignerProducts = designerProductService.getMyDesignerProducts(designerId, status);
        return BaseResponse.success(myDesignerProducts);
    }

    // Model -> Designer 프로필 조회
    @GetMapping("/{designerProductId}")
    public BaseResponse<DesignerProductProfileDto> getModelToDesignerProfile(@PathVariable Long designerProductId){
        return BaseResponse.success(designerProductService.getModelToDesignerProfile(designerProductId));
    }

}
