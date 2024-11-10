package com.mozipp.product.domain.request.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.test.model.entity.Model;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    private RequestStatus status = RequestStatus.PENDING;

    private String modelDescription;
    private LocalDateTime reservationRequestDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToOne
    @JoinColumn(name = "designer_product_id")
    private DesignerProduct designerProduct;

    @OneToOne(mappedBy = "reservationRequest")
    private Reservation reservation;

    public void accept(){
        this.status = RequestStatus.ACCEPTED;
    }

    public void reject(){
        this.status = RequestStatus.REJECTED;
    }

    @Builder
    public ReservationRequest(RequestStatus status, String modelDescription, LocalDateTime reservationRequestDate, Model model, DesignerProduct designerProduct, Reservation reservation) {
        this.status = status;
        this.modelDescription = modelDescription;
        this.reservationRequestDate = reservationRequestDate;
        this.model = model;
        this.designerProduct = designerProduct;
        this.reservation = reservation;
    }

    public void associateReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
