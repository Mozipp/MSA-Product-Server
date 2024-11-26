package com.mozipp.product.domain.request.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.dto.*;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.PetShop;
import com.mozipp.product.users.PetShopDto;

import java.util.List;

public class ReservationRequestConverter {

    public static DesignerRequestListDto toDesignerRequestListDto(ReservationRequest request, List<ReviewDto> reviews) {
        Model model = request.getModel();

        ReservationRequestModelDto modelDto = ReservationRequestModelDto.builder()
                .modelDescription(request.getModelDescription())
                .petName(model.getPetName())
                .petAge(model.getPetAge())
                .petGender(model.getPetGender())
                .breed(model.getBreed())
                .petImageUrl(model.getPetImageUrl())
                .reviews(reviews)
                .build();

        return DesignerRequestListDto.builder()
                .reservationRequestId(request.getId())
                .designerProductId(request.getDesignerProduct().getId())
                .title(request.getDesignerProduct().getTitle())
                .reservationRequestStatus(request.getRequestStatus())
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
                .requestStatus(RequestStatus.PENDING)
                .build();
    }

    public static ModelRequestListDto toModelRequestListDto(ReservationRequest request, RequestProductDto requestProductDto) {
        return ModelRequestListDto.builder()
                .reservationRequestId(request.getId())
                .reservationRequestStatus(request.getRequestStatus())
                .modelDescription(request.getModelDescription())
                .reservationRequestDate(request.getReservationRequestDate())
                .designerProduct(requestProductDto)
                .createdAt(request.getCreatedAt())
                .build();
    }

    public static RequestProductDto toRequestProductDto(ReservationRequest request) {
        DesignerProduct designerProduct = request.getDesignerProduct();
        PetShop petShop = designerProduct.getDesigner().getPetShop();
        PetShopDto petShopDto = PetShopDto.builder()
                .petShopName(petShop.getPetShopName())
                .address(petShop.getAddress())
                .addressDetail(petShop.getAddressDetail())
                .build();
        return RequestProductDto.builder()
                .designerProductId(designerProduct.getId())
                .title(designerProduct.getTitle())
                .description(designerProduct.getDescription())
                .design(designerProduct.getDesign())
                .modelPreferDescription(designerProduct.getModelPreferDescription())
                .preferBreed(designerProduct.getPreferBreed())
                .petShop(petShopDto)
                .build();
    }
}