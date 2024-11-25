package com.mozipp.product.domain.request.controller;

import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.domain.request.dto.DesignerRequestListDto;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.service.DesignerRequestService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/reservation-request")
public class DesignerRequestController {

    private final DesignerRequestService designerReservationRequestService;
    private final UserFindService userFindService;

    // Designer 예약 요청 리스트 조회
    @GetMapping
    public BaseResponse<List<DesignerRequestListDto>> getReservationRequestList(@RequestParam("status") RequestStatus status, @RequestHeader("Authorization") String authorizationHeader) {
        Long designerId = userFindService.getUserId(authorizationHeader);
        List<DesignerRequestListDto> designerRequestList = designerReservationRequestService.getReservationRequestList(designerId, status);
        return BaseResponse.success(designerRequestList);
    }

    // Designer 예약 요청 수락
    @PostMapping("/{reservationRequestId}/accept")
    public BaseResponse<Object> acceptReservationRequest(@PathVariable Long reservationRequestId) {
        designerReservationRequestService.acceptReservationRequest(reservationRequestId);
        return BaseResponse.success();
    }

    // Designer 예약 요청 거절
    @PostMapping("/{reservationRequestId}/reject")
    public BaseResponse<Object> rejectReservationRequest(@PathVariable Long reservationRequestId) {
        designerReservationRequestService.rejectReservationRequest(reservationRequestId);
        return BaseResponse.success();
    }
}
