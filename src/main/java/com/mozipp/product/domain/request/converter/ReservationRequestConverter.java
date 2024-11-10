package com.mozipp.product.domain.request.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.dto.ModelReservationRequestDto;
import com.mozipp.product.domain.request.dto.ReservationRequestListDto;
import com.mozipp.product.domain.request.dto.ReservationRequestModelDto;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.test.model.entity.Model;

import java.util.List;

public class ReservationRequestConverter {

    public static ReservationRequestListDto toDesignerReservationRequestDto(ReservationRequest request, List<ReviewDto> reviews) {
        Model model = request.getModel();

        ReservationRequestModelDto modelDto = ReservationRequestModelDto.builder()
                .modelDescription(request.getModelDescription())
                .petName(model.getPetName())
                .petAge(model.getPetAge())
                .breed(model.getBreed())
                .petImageUrl(model.getPetImageUrl())
                .reviews(reviews)
                .build();

        return ReservationRequestListDto.builder()
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
}