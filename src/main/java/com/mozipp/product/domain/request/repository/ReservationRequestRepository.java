package com.mozipp.product.domain.request.repository;

import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.test.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    List<ReservationRequest> findByModel_Id(Long modelId);

    List<ReservationRequest> findByDesignerProduct_Designer_Id(Long designerId);

    List<ReservationRequest> findAllByModel(Model model);
}
