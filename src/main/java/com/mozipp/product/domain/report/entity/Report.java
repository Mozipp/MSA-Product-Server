package com.mozipp.product.domain.report.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    private String reportContent;
    private Long reportedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_product_id")
    private DesignerProduct designerProduct;
}
