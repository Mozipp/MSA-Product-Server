package com.mozipp.product.domain.request.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.dto.*;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.test.model.entity.Model;

import java.util.List;

public class ReservationRequestConverter {

    public static DesignerRequestListDto toDesignerRequestListDto(ReservationRequest request, List<ReviewDto> reviews) {
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


    public static ReservationRequest toReservationRequest(Model model, DesignerProduct designerProduct, ModelRequestCreateDto request) {
        return ReservationRequest.builder()
                .model(model)
                .designerProduct(designerProduct)
                .modelDescription(request.getModelDescription())
                .reservationRequestDate(request.getReservationRequestDate())
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