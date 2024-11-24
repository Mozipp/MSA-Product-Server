package com.mozipp.product.domain.request.repository;

import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    List<ReservationRequest> findByDesignerProduct_Designer_Id(Long designerId);
    List<ReservationRequest> findByDesignerProduct_Designer_IdAndRequestStatus(Long designerId, RequestStatus status);
    ReservationRequest findByDesignerProduct_IdAndRequestStatus(Long designerProductId, RequestStatus requestStatus);
}
