package com.mozipp.product.domain.product.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.domain.report.entity.Report;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.review.entity.Review;
import com.mozipp.product.test.designer.entity.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    private Long id;

    private String title;
    private String description;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "designerProduct")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "designerProduct")
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "designerProduct")
    private ReservationRequest reservationRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Designer designer;

    @Builder
    public DesignerProduct(String title, String description, String design, String modelPreferDescription, String preferBreed, ProductStatus productStatus) {
        this.title = title;
        this.description = description;
        this.design = design;
        this.modelPreferDescription = modelPreferDescription;
        this.preferBreed = preferBreed;
        this.productStatus = productStatus;
    }

    public void updateDesigner(Designer designer) {
        this.designer = designer;
        designer.addDesignerProduct(this);
    }

    public void updateProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
