package com.mozipp.product.domain.reservation.entity;

import com.mozipp.product.domain.request.entity.ReservationRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private LocalDateTime reservationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_request_id")
    private ReservationRequest reservationRequest;

    @Builder
    public Reservation(LocalDateTime reservationDate, ReservationRequest reservationRequest, ReservationStatus reservationStatus) {
        this.reservationDate = reservationDate;
        this.reservationRequest = reservationRequest;
        this.reservationStatus = reservationStatus;
    }

    public void updateStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
