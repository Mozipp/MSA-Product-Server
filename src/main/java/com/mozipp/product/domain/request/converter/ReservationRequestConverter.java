package com.mozipp.product.domain.request.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.dto.*;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.test.model.entity.Model;

import java.util.List;

public class ReservationRequestConverter {

    public static DesignerRequestListDto toDesignerReservationRequestDto(ReservationRequest request, List<ReviewDto> reviews) {
        Model model = request.getModel();

        ReservationRequestModelDto modelDto = ReservationRequestModelDto.builder()
                .modelDescription(request.getModelDescription())
                .petName(model.getPetName())
                .petAge(model.getPetAge())
                .breed(model.getBreed())
                .petImageUrl(model.getPetImageUrl())
                .reviews(reviews)
                .build();

        return DesignerRequestListDto.builder()
                .reservationRequestId(request.getId())
                .reservationRequestStatus(request.getStatus())
                .model(modelDto)
                .reservationRequestDate(request.getReservationRequestDate())
                .createdAt(request.getCreatedAt())
                .build();
    }


    public static ReservationRequest toModelReservationRequestDto(Model model, DesignerProduct designerProduct, ModelReservationRequestDto request) {
        return ReservationRequest.builder()
                .model(model)
                .designerProduct(designerProduct)
                .modelDescription(request.getModelDescription())
                .reservationRequestDate(request.getReservationRequestDate())
                .status(RequestStatus.PENDING)
                .build();
    }

    public static ModelRequestListDto toModelRequestListDto(ReservationRequest request) {
        return ModelRequestListDto.builder()
                .reservationRequestId(request.getId())
                .reservationRequestStatus(request.getStatus())
                .modelDescription(request.getModelDescription())
                .reservationRequestDate(request.getReservationRequestDate())
                .createdAt(request.getCreatedAt())
                .build();
    }
}