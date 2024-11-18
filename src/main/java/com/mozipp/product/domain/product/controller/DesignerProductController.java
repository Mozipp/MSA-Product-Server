package com.mozipp.product.domain.product.controller;

import com.mozipp.product.domain.product.dto.DesignerProductCreateDto;
import com.mozipp.product.domain.product.dto.DesignerProductListDto;
import com.mozipp.product.domain.product.dto.DesignerProductProfileDto;
import com.mozipp.product.domain.product.service.DesignerProductService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.designer.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer-product")
public class DesignerProductController {

    private final DesignerProductService designerProductService;
    private final DesignerRepository designerRepository;

    // Designer 상품 페이지 목록 조회
    @GetMapping
    public BaseResponse<List<DesignerProductListDto>> getDesignerProducts() {
        return BaseResponse.success(designerProductService.getDesignerProducts());
    }

    // Designer 상품 등록
    @PostMapping
    public BaseResponse<Object> createDesignerProduct(@RequestBody DesignerProductCreateDto request){
        designerProductService.createDesignerProduct(request);
        return BaseResponse.success();
    }

    // Designer 본인이 등록한 상품 페이지 목록 조회
    @GetMapping("/my-product/{designerId}")
    public BaseResponse<List<DesignerProductListDto>> getMyDesignerProducts(@PathVariable Long designerId){
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND));
        return BaseResponse.success(designerProductService.getMyDesignerProducts(designer));
    }

    // Model -> Designer 프로필 조회
    @GetMapping("/{designerProductId}")
    public BaseResponse<DesignerProductProfileDto> getModelToDesignerProfile(@PathVariable Long designerProductId){
        return BaseResponse.success(designerProductService.getModelToDesignerProfile(designerProductId));
    }

}
