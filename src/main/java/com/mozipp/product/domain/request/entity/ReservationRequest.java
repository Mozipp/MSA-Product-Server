package com.mozipp.product.domain.request.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.domain.product.entity.DesignerProduct;
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
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private String modelDescription;
    private LocalDateTime reservationRequestDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_product_id")
    private DesignerProduct designerProduct;

    @Builder
    public ReservationRequest(String modelDescription, LocalDateTime reservationRequestDate, Model model, DesignerProduct designerProduct, RequestStatus requestStatus) {
        this.modelDescription = modelDescription;
        this.reservationRequestDate = reservationRequestDate;
        this.model = model;
        this.designerProduct = designerProduct;
        this.requestStatus = requestStatus;
    }

    public void updateRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
