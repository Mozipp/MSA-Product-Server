package com.mozipp.product.domain.reservation.controller;

import com.mozipp.product.domain.reservation.dto.ModelReservationListDto;
import com.mozipp.product.domain.reservation.service.ModelReservationService;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.test.user.entity.User;
import com.mozipp.product.test.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model/reservation")
public class ModelReservationController {

    private final UserFindService userFindService;
    private final ModelReservationService modelReservationService;

    // Model 예약 확정 리스트 조회
    public BaseResponse<List<ModelReservationListDto>> getModelReservationList(@AuthenticationPrincipal UserDetails userDetails){
        User user = userFindService.findByUserDetails(userDetails);
        return BaseResponse.success(modelReservationService.getModelReservationList(user));
    }
}
