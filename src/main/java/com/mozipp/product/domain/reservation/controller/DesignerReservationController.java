package com.mozipp.product.domain.reservation.controller;

import com.mozipp.product.domain.reservation.dto.DesignerReservationListDto;
import com.mozipp.product.domain.reservation.service.DesignerReservationService;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.test.user.entity.User;
import com.mozipp.product.test.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/designer/reservation")
public class DesignerReservationController {

    private final UserFindService userFindService;
    private final DesignerReservationService designerReservationService;

    @GetMapping
    public BaseResponse<List<DesignerReservationListDto>> getDesignerReservationList(@AuthenticationPrincipal UserDetails userDetails) {
        User user =  userFindService.findByUserDetails(userDetails);
        return BaseResponse.success(designerReservationService.getDesignerReservationList(user));
    }
 }
