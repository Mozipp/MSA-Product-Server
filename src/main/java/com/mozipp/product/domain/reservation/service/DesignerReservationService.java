package com.mozipp.product.domain.reservation.service;

import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.reservation.converter.ReservationConverter;
import com.mozipp.product.domain.reservation.dto.DesignerReservationListDto;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.domain.reservation.repository.ReservationRepository;
import com.mozipp.product.domain.review.service.ModelReviewService;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.model.entity.Model;
import com.mozipp.product.test.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelReviewService modelReviewService;

    public List<DesignerReservationListDto> getDesignerReservationList(User user) {
        Designer designer = (Designer) user;
        List<Reservation> reservations = reservationRepository.findAllByReservationRequest_DesignerProduct_Designer(designer);
        List<DesignerReservationListDto> designerReservationListDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationRequest request = reservation.getReservationRequest();
            Model model = request.getModel();
            List<ReviewDto> reviews = modelReviewService.getReviewsForModel(model.getId());

            DesignerReservationListDto dto = ReservationConverter.toDesignerReservationListDto(reservation, reviews);
            designerReservationListDtos.add(dto);
        }

        return designerReservationListDtos;
    }
}
