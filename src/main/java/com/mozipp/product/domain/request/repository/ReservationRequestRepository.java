package com.mozipp.product.domain.request.repository;

import com.mozipp.product.domain.request.entity.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    Optional<ReservationRequest> findByModel_Id(Long modelId);
    List<ReservationRequest> findAllByModel_Id(Long modelId);
    List<ReservationRequest> findByDesignerProduct_Designer_Id(Long designerId);

}
