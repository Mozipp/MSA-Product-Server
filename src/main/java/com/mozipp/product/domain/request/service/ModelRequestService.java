package com.mozipp.product.domain.request.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.request.converter.ReservationRequestConverter;
import com.mozipp.product.domain.request.dto.ModelRequestListDto;
import com.mozipp.product.domain.request.dto.ModelRequestCreateDto;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.model.entity.Model;
import com.mozipp.product.test.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelRequestService {

    private final ReservationRequestRepository reservationRequestRepository;
    private final DesignerProductRepository designerProductRepository;

    @Transactional
    public void createModelReservationRequest(User user, ModelRequestCreateDto request) {
        Model model = (Model) user;

        DesignerProduct designerProduct = designerProductRepository.findById(request.getDesignerProductId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));

        ReservationRequest reservationRequest = ReservationRequestConverter.toModelReservationRequestDto(model, designerProduct, request);
        reservationRequestRepository.save(reservationRequest);

        designerProduct.updateProductStatus(ProductStatus.RESERVED);
    }

    public List<ModelRequestListDto> getModelReservationRequest(User user) {
        Model model = (Model) user;

        List<ReservationRequest> reservationRequests = reservationRequestRepository.findAllByModel_Id(model.getId());
        List<ModelRequestListDto> modelRequestListDtos = new ArrayList<>();

        for (ReservationRequest request : reservationRequests) {
            ModelRequestListDto dto = ReservationRequestConverter.toModelRequestListDto(request);
            modelRequestListDtos.add(dto);
        }

        return modelRequestListDtos;
    }
}
