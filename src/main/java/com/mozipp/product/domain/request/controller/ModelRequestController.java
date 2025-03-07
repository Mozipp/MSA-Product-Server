package com.mozipp.product.domain.request.controller;

import com.mozipp.product.domain.request.dto.ModelRequestCreateDto;
import com.mozipp.product.domain.request.dto.ModelRequestListDto;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.service.ModelRequestService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/reservation-request")
public class ModelRequestController {

    private final ModelRequestService modelRequestService;

    // Model 예약 요청
    @PostMapping
    public BaseResponse<Object> createModelReservationRequest(@RequestBody ModelRequestCreateDto request, @AuthenticationPrincipal Long modelId){
        modelRequestService.createModelReservationRequest(modelId, request);
        return BaseResponse.success();
    }

    // Model 예약 요청 리스트 조회
    @GetMapping
    public BaseResponse<List<ModelRequestListDto>> getModelReservationRequest(@RequestParam("status") RequestStatus status, @AuthenticationPrincipal Long modelId) {
        return BaseResponse.success(modelRequestService.getModelReservationRequest(modelId, status));
    }

}
