package com.mozipp.product.domain.request.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.request.converter.ReservationRequestConverter;
import com.mozipp.product.domain.request.dto.DesignerRequestListDto;
import com.mozipp.product.domain.request.dto.ReviewDto;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.domain.reservation.repository.ReservationRepository;
import com.mozipp.product.domain.review.service.ModelReviewService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.global.notification.NotificationService;
import com.mozipp.product.users.Designer;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.repository.DesignerRepository;
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
    private final DesignerRepository designerRepository;
    private final NotificationService notificationService;

    @Transactional
    public void acceptReservationRequest(Long reservationRequestId) {
        ReservationRequest request = reservationRequestRepository.findById(reservationRequestId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION_REQUEST));

        request.updateRequestStatus(RequestStatus.ACCEPTED);

        DesignerProduct designerProduct = request.getDesignerProduct();
        designerProduct.updateProductStatus(ProductStatus.UNAVAILABLE);

        Reservation reservation = Reservation.builder()
                .reservationDate(request.getReservationRequestDate())
                .reservationRequest(request)
                .reservationStatus(ReservationStatus.CONFIRMED)
                .build();

        reservationRepository.save(reservation);

        Model model = request.getModel(); // ReservationRequest에 Model 참조가 있다고 가정
        Long modelId = model.getId();
        String message = "귀하의 예약 요청이 수락되었습니다: " + designerProduct.getTitle();
        notificationService.sendNotification(modelId, "reservation-accepted", message);
    }

    @Transactional
    public void rejectReservationRequest(Long reservationRequestId) {
        ReservationRequest request = reservationRequestRepository.findById(reservationRequestId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION_REQUEST));

        request.updateRequestStatus(RequestStatus.REJECTED);

        DesignerProduct designerProduct = request.getDesignerProduct();
        designerProduct.updateProductStatus(ProductStatus.AVAILABLE);

        // 모델에게 알림 트리거
        Model model = request.getModel(); // ReservationRequest에 Model 참조가 있다고 가정
        Long modelId = model.getId();
        String message = "귀하의 예약 요청이 거절되었습니다: " + designerProduct.getTitle();
        notificationService.sendNotification(modelId, "reservation-rejected", message);
    }

    public List<DesignerRequestListDto> getReservationRequestList(Long designerId, RequestStatus status) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));
        List<ReservationRequest> reservationRequests = reservationRequestRepository.findByDesignerProduct_Designer_IdAndRequestStatus(designer.getId(), status);
        List<DesignerRequestListDto> reservationRequestList = new ArrayList<>();

        for(ReservationRequest request : reservationRequests) {
            Long modelId = request.getModel().getId();
            List<ReviewDto> reviews = modelReviewService.getReviewsForModel(modelId);
            DesignerRequestListDto dto = ReservationRequestConverter.toDesignerRequestListDto(request, reviews);
            reservationRequestList.add(dto);
        }

        return reservationRequestList;
    }
}
