package com.mozipp.product.domain.request.repository;

import com.mozipp.product.domain.request.entity.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
}
