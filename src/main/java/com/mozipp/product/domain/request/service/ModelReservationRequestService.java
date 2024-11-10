package com.mozipp.product.domain.request.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.request.converter.ReservationRequestConverter;
import com.mozipp.product.domain.request.dto.ModelReservationRequestDto;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.model.entity.Model;
import com.mozipp.product.test.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModelReservationRequestService {

    private final ReservationRequestRepository reservationRequestRepository;
    private final DesignerProductRepository designerProductRepository;

    @Transactional
    public void modelReservationRequest(User user, ModelReservationRequestDto request) {
        Model model = (Model) user;

        DesignerProduct designerProduct = designerProductRepository.findById(request.getDesignerProductId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));

        ReservationRequest reservationRequest = ReservationRequestConverter.toModelReservationRequestDto(model, designerProduct, request);
        reservationRequestRepository.save(reservationRequest);

        designerProduct.updateProductStatus(ProductStatus.RESERVED);
    }
}
