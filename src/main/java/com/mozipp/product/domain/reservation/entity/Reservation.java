package com.mozipp.product.domain.reservation.entity;

import com.mozipp.product.domain.request.entity.ReservationRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime reservationDate;

    @OneToOne
    @JoinColumn(name = "reservation_request_id")
    private ReservationRequest reservationRequest;
}
