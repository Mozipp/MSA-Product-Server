package com.mozipp.product.domain.request.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationRequest extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_request_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    private String modelDescription;
    private LocalDateTime reservationRequestDate;

    @OneToOne
    @JoinColumn(name = "designer_product_id")
    private DesignerProduct designerProduct;

    @OneToOne(mappedBy = "reservationRequest")
    private Reservation reservation;

}
