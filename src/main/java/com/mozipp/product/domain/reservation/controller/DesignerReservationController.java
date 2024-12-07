package com.mozipp.product.domain.reservation.controller;

import com.mozipp.product.domain.reservation.dto.DesignerReservationHandleDto;
import com.mozipp.product.domain.reservation.dto.DesignerReservationListDto;
import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.domain.reservation.service.DesignerReservationService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/reservation")
public class DesignerReservationController {

    private final DesignerReservationService designerReservationService;

    // Designer 예약 확정 리스트 조회
    @GetMapping
    public BaseResponse<List<DesignerReservationListDto>> getDesignerReservationList(@RequestParam("status") ReservationStatus status, @AuthenticationPrincipal Long designerId) {
        return BaseResponse.success(designerReservationService.getDesignerReservationList(designerId, status));
    }

    // Designer 예약 확정 처리
    @PostMapping("/{reservationId}")
    public BaseResponse<Object> handleReservation(@RequestBody DesignerReservationHandleDto request, @PathVariable Long reservationId) {
        designerReservationService.handleReservation(request, reservationId);
        return BaseResponse.success();
    }
 }
