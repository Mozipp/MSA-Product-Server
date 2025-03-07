package com.mozipp.product.domain.reservation.controller;

import com.mozipp.product.domain.reservation.dto.ModelReservationListDto;
import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.domain.reservation.service.ModelReservationService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/reservation")
public class ModelReservationController {

    private final ModelReservationService modelReservationService;

    // Model 예약 확정 리스트 조회
    @GetMapping
    public BaseResponse<List<ModelReservationListDto>> getModelReservationList(@RequestParam("status") ReservationStatus status,@AuthenticationPrincipal Long modelId) {
        return BaseResponse.success(modelReservationService.getModelReservationList(modelId, status));
    }
}
