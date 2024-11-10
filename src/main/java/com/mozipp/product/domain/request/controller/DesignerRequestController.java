package com.mozipp.product.domain.request.controller;

import com.mozipp.product.domain.request.dto.DesignerRequestListDto;
import com.mozipp.product.domain.request.service.DesignerRequestService;
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
@RequestMapping("/api/designer/reservation-request")
public class DesignerRequestController {

    private final DesignerRequestService designerReservationRequestService;
    private final UserFindService userFindService;

    // Designer 예약 요청 리스트 조회
    @GetMapping
    public BaseResponse<List<DesignerRequestListDto>> getReservationRequestList(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        List<DesignerRequestListDto> response = designerReservationRequestService.getReservationRequestList(user);
        return BaseResponse.success(response);
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
