package com.mozipp.product.domain.report.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportContent;
    private Long userId;
    private Long targetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_product_id")
    private DesignerProduct designerProduct;

    @Builder
    public Report(String reportContent, Long userId, Long targetId, DesignerProduct designerProduct) {
        this.reportContent = reportContent;
        this.userId = userId;
        this.targetId = targetId;
        this.designerProduct = designerProduct;
    }
}
