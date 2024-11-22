package com.mozipp.product.domain.product.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.users.Designer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindService {
    private final ReservationRequestRepository reservationRequestRepository;

    public Long getModelId(Long designerProductId){
        ReservationRequest reservationRequest = reservationRequestRepository.findByDesignerProduct_IdAndRequestStatus(designerProductId, RequestStatus.ACCEPTED);
        return reservationRequest.getModel().getId();
    }

    public Long getDesignerId(DesignerProduct designerProduct){
        Designer designer = designerProduct.getDesigner();
        return designer.getId();
    }
}
