package com.mozipp.product.domain.request.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.request.converter.ReservationRequestConverter;
import com.mozipp.product.domain.request.dto.DesignerRequestListDto;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.domain.reservation.repository.ReservationRepository;
import com.mozipp.product.domain.review.service.ModelReviewService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerRequestService {

    private final ReservationRequestRepository reservationRequestRepository;
    private final ReservationRepository reservationRepository;
    private final ModelReviewService modelReviewService;

    @Transactional
    public void acceptReservationRequest(Long reservationRequestId) {
        ReservationRequest request = reservationRequestRepository.findById(reservationRequestId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION_REQUEST));

        request.accept();

        DesignerProduct designerProduct = request.getDesignerProduct();
        designerProduct.updateProductStatus(ProductStatus.RESERVED);

        Reservation reservation = Reservation.builder()
                .reservationDate(request.getReservationRequestDate())
                .reservationRequest(request)
                .build();

        reservationRepository.save(reservation);

        reservationRequestRepository.save(request);
    }

    @Transactional
    public void rejectReservationRequest(Long reservationRequestId) {
        ReservationRequest request = reservationRequestRepository.findById(reservationRequestId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION_REQUEST));

        request.reject();

        DesignerProduct designerProduct = request.getDesignerProduct();
        designerProduct.updateProductStatus(ProductStatus.AVAILABLE);

        reservationRequestRepository.save(request);
    }

    public List<DesignerRequestListDto> getReservationRequestList(User user) {
        Designer designer = (Designer) user;
        List<ReservationRequest> reservationRequests = reservationRequestRepository.findByDesignerProduct_Designer_Id(designer.getId());
        List<DesignerRequestListDto> reservationRequestList = new ArrayList<>();

        for(ReservationRequest request : reservationRequests) {
            Long modelId = request.getModel().getId();
            List<ReviewDto> reviews = modelReviewService.getReviewsForModel(modelId);
            DesignerRequestListDto dto = ReservationRequestConverter.toDesignerReservationRequestDto(request, reviews);
            reservationRequestList.add(dto);
        }

        return reservationRequestList;
    }
}
