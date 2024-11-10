package com.mozipp.product.domain.reservation.repository;


import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.test.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByReservationRequest_Model(Model model);
}
