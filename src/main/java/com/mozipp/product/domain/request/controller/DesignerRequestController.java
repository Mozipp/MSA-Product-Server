package com.mozipp.product.domain.request.controller;

import com.mozipp.product.domain.request.dto.DesignerRequestListDto;
import com.mozipp.product.domain.request.service.DesignerRequestService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.designer.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/reservation-request")
public class DesignerRequestController {

    private final DesignerRequestService designerReservationRequestService;
    private final DesignerRepository designerRepository;

    // Designer 예약 요청 리스트 조회
    @GetMapping("/{designerId}")
    public BaseResponse<List<DesignerRequestListDto>> getReservationRequestList(@PathVariable Long designerId) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND));
        List<DesignerRequestListDto> designerRequestListDtos = designerReservationRequestService.getReservationRequestList(designer);
        return BaseResponse.success(designerRequestListDtos);
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
