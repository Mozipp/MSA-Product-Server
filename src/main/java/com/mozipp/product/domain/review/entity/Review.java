package com.mozipp.product.domain.review.entity;

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
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String reviewContent;
    private Long reviewee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_product_id")
    private DesignerProduct designerProduct;

    @Builder
    public Review(String reviewContent, Long reviewee, DesignerProduct designerProduct) {
        this.reviewContent = reviewContent;
        this.reviewee = reviewee;
        this.designerProduct = designerProduct;
    }
}
