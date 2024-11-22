package com.mozipp.product.domain.request.controller;

import com.mozipp.product.domain.request.dto.ModelRequestCreateDto;
import com.mozipp.product.domain.request.dto.ModelRequestListDto;
import com.mozipp.product.domain.request.service.ModelRequestService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/reservation-request")
public class ModelRequestController {

    private final ModelRequestService modelRequestService;
    private final ModelRepository modelRepository;

    // Model 예약 요청
    @PostMapping
    public BaseResponse<Object> createModelReservationRequest(@RequestBody ModelRequestCreateDto request){
        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        modelRequestService.createModelReservationRequest(model, request);
        return BaseResponse.success();
    }

    // Model 예약 요청 리스트 조회
    @GetMapping("/{modelId}")
    public BaseResponse<List<ModelRequestListDto>> getModelReservationRequest(@PathVariable Long modelId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        return BaseResponse.success(modelRequestService.getModelReservationRequest(model));
    }

}
