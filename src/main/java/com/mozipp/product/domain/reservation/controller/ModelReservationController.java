package com.mozipp.product.domain.reservation.controller;

import com.mozipp.product.domain.reservation.dto.ModelReservationListDto;
import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.domain.reservation.service.ModelReservationService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/reservation")
public class ModelReservationController {

    private final ModelReservationService modelReservationService;
    private final ModelRepository modelRepository;

    // Model 예약 확정 리스트 조회
    @GetMapping("/{modelId}")
    public BaseResponse<List<ModelReservationListDto>> getModelReservationList(@PathVariable Long modelId, @RequestParam("status") ReservationStatus status) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        return BaseResponse.success(modelReservationService.getModelReservationList(model, status));
    }
}
