package com.mozipp.product.domain.reservation.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.reservation.dto.DesignerReservationListDto;
import com.mozipp.product.domain.reservation.dto.ModelReservationListDto;
import com.mozipp.product.domain.reservation.dto.ReservationListModelDto;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.users.PetShop;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.PetShopDto;

import java.util.List;

public class ReservationConverter {

    public static ModelReservationListDto toModelReservationListDto(Reservation reservation) {
        ReservationRequest request = reservation.getReservationRequest();
        DesignerProduct designerProduct = request.getDesignerProduct();
        PetShop petShop = designerProduct.getDesigner().getPetShop();

        PetShopDto petShopDto = PetShopDto.builder()
                .petShopName(petShop.getPetShopName())
                .address(petShop.getAddress())
                .addressDetail(petShop.getAddressDetail())
                .build();

        return ModelReservationListDto.builder()
                .reservationId(reservation.getId())
                .designerProductId(designerProduct.getId())
                .petShop(petShopDto)
                .design(designerProduct.getDesign())
                .reservationStatus(reservation.getReservationStatus())
                .reservationRequestDate(request.getReservationRequestDate())
                .createdAt(request.getCreatedAt())
                .build();
    }

    public static DesignerReservationListDto toDesignerReservationListDto(Reservation reservation, List<ReviewDto> reviews) {
        ReservationRequest request = reservation.getReservationRequest();
        DesignerProduct designerProduct = request.getDesignerProduct();
        Model model = request.getModel();

        ReservationListModelDto modelDto = ReservationListModelDto.builder()
                .modelDescription(request.getModelDescription())
                .petName(model.getPetName())
                .petAge(model.getPetAge())
                .petGender(model.getPetGender())
                .breed(model.getBreed())
                .petImageUrl(model.getPetImageUrl())
                .reviews(reviews)
                .build();

        return DesignerReservationListDto.builder()
                .reservationId(reservation.getId())
                .design(designerProduct.getDesign())
                .model(modelDto)
                .reservationStatus(reservation.getReservationStatus())
                .reservationDate(reservation.getReservationDate())
                .createdAt(request.getCreatedAt())
                .build();
    }
}
