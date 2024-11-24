package com.mozipp.product.domain.reservation.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.reservation.converter.ReservationConverter;
import com.mozipp.product.domain.reservation.dto.DesignerReservationHandleDto;
import com.mozipp.product.domain.reservation.dto.DesignerReservationListDto;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.domain.reservation.repository.ReservationRepository;
import com.mozipp.product.domain.review.service.ModelReviewService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Designer;
import com.mozipp.product.users.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelReviewService modelReviewService;

    public List<DesignerReservationListDto> getDesignerReservationList(Designer designer, ReservationStatus status) {

        List<Reservation> reservations = reservationRepository.findAllByReservationRequest_DesignerProduct_Designer(designer);
        List<DesignerReservationListDto> designerReservationListDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if(reservation.getReservationStatus() == status) {
                ReservationRequest request = reservation.getReservationRequest();
                Model model = request.getModel();
                List<ReviewDto> reviews = modelReviewService.getReviewsForModel(model.getId());

                DesignerReservationListDto dto = ReservationConverter.toDesignerReservationListDto(reservation, reviews);
                designerReservationListDtos.add(dto);
            }
        }

        return designerReservationListDtos;
    }

    @Transactional
    public void handleReservation(DesignerReservationHandleDto request, Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION));
        reservation.updateStatus(request.getReservationStatus());

        ReservationRequest reservationRequest = reservation.getReservationRequest();
        DesignerProduct designerProduct = reservationRequest.getDesignerProduct();

        if (request.getReservationStatus() == ReservationStatus.CANCELED) {
            reservationRequest.updateRequestStatus(RequestStatus.CANCELED);
            designerProduct.updateProductStatus(ProductStatus.UNAVAILABLE);
        }
    }
}
