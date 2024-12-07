package com.mozipp.product.domain.product.entity;

import com.mozipp.product.domain.BaseTimeEntity;
import com.mozipp.product.users.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String introduction;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Designer designer;

    @Builder
    public DesignerProduct(String title, String introduction, String design, String modelPreferDescription, String preferBreed, ProductStatus productStatus, TransactionStatus transactionStatus) {
        this.title = title;
        this.introduction = introduction;
        this.design = design;
        this.modelPreferDescription = modelPreferDescription;
        this.preferBreed = preferBreed;
        this.productStatus = productStatus;
        this.transactionStatus = transactionStatus;
    }

    public void updateDesigner(Designer designer) {
        this.designer = designer;
        designer.addDesignerProduct(this);
    }

    public void updateProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public void updateTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
