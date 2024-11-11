package com.mozipp.product.domain.request.service;

import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindModelService {

    private final ReservationRequestRepository reservationRequestRepository;

    public Long getDesignerProductIdForModel(Long designerProductId) {
        ReservationRequest reservationRequest = reservationRequestRepository.findByDesignerProduct_Id(designerProductId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION_REQUEST));

        return reservationRequest.getModel().getId();
    }
}
