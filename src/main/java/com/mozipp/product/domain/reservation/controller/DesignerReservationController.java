package com.mozipp.product.domain.reservation.controller;

import com.mozipp.product.domain.reservation.dto.DesignerReservationHandleDto;
import com.mozipp.product.domain.reservation.dto.DesignerReservationListDto;
import com.mozipp.product.domain.reservation.service.DesignerReservationService;
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
@RequestMapping("/api/products/designer/reservation")
public class DesignerReservationController {

    private final UserFindService userFindService;
    private final DesignerReservationService designerReservationService;

    // Designer 예약 확정 리스트 조회
    @GetMapping
    public BaseResponse<List<DesignerReservationListDto>> getDesignerReservationList(@AuthenticationPrincipal UserDetails userDetails) {
        User user =  userFindService.findByUserDetails(userDetails);
        return BaseResponse.success(designerReservationService.getDesignerReservationList(user));
    }

    // Designer 예약 확정 처리
    @PostMapping("/{reservationId}")
    public BaseResponse<Object> handleReservation(@RequestBody DesignerReservationHandleDto request, @AuthenticationPrincipal UserDetails userDetails, @PathVariable String reservationId) {
        User user = userFindService.findByUserDetails(userDetails);
        designerReservationService.handleReservation(request, user, reservationId);
        return BaseResponse.success();
    }
 }
