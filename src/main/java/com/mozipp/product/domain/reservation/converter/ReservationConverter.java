package com.mozipp.product.domain.reservation.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.reservation.dto.ModelReservationListDto;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.test.designer.dto.PetShopDto;
import com.mozipp.product.test.petshop.entity.PetShop;

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
                .petShop(petShopDto)
                .design(designerProduct.getDesign())
                .reservationStatus(reservation.getStatus())
                .reservationRequestDate(request.getReservationRequestDate())
                .createdAt(request.getCreatedAt())
                .build();
    }
}
