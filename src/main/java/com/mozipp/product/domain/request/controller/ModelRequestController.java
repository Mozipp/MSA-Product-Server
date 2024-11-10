package com.mozipp.product.domain.request.controller;

import com.mozipp.product.domain.request.dto.ModelRequestListDto;
import com.mozipp.product.domain.request.dto.ModelReservationRequestDto;
import com.mozipp.product.domain.request.service.ModelRequestService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model/reservation-request")
public class ModelRequestController {

    private final ModelRequestService modelRequestService;
    private final UserFindService userFindService;

    // Model 예약 요청
    @PostMapping
    public BaseResponse<Object> createModelReservationRequest(@RequestBody ModelReservationRequestDto request, @AuthenticationPrincipal UserDetails userDetails){
        User user = userFindService.findByUserDetails(userDetails);
        modelRequestService.createModelReservationRequest(user, request);
        return BaseResponse.success();
    }

    // Model 예약 요청 리스트 조회
    public BaseResponse<List<ModelRequestListDto>> getModelReservationRequest(@AuthenticationPrincipal UserDetails userDetails){
        User user = userFindService.findByUserDetails(userDetails);
        return BaseResponse.success(modelRequestService.getModelReservationRequest(user));
    }

}
