package com.mozipp.product.domain.product.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.domain.report.entity.Report;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "designer_product_id")
    private Long id;

    private String title;
    private String description;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
    private String reservationDate; // 미정

    @OneToMany(mappedBy = "designerProduct")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "designerProduct")
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "designerProduct")
    private ReservationRequest reservationRequest;
}
