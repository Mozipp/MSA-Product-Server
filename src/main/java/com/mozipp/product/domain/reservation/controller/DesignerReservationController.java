package com.mozipp.product.domain.reservation.controller;

import com.mozipp.product.domain.reservation.dto.DesignerReservationHandleDto;
import com.mozipp.product.domain.reservation.dto.DesignerReservationListDto;
import com.mozipp.product.domain.reservation.service.DesignerReservationService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Designer;
import com.mozipp.product.users.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/reservation")
public class DesignerReservationController {

    private final DesignerReservationService designerReservationService;
    private final DesignerRepository designerRepository;

    // Designer 예약 확정 리스트 조회
    @GetMapping("/{designerId}")
    public BaseResponse<List<DesignerReservationListDto>> getDesignerReservationList(@PathVariable Long designerId) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));
        return BaseResponse.success(designerReservationService.getDesignerReservationList(designer));
    }

    // Designer 예약 확정 처리
    @PostMapping("/{reservationId}")
    public BaseResponse<Object> handleReservation(@RequestBody DesignerReservationHandleDto request, @PathVariable Long reservationId) {
        designerReservationService.handleReservation(request, reservationId);
        return BaseResponse.success();
    }
 }
