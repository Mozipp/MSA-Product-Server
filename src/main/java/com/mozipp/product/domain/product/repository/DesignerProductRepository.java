package com.mozipp.product.domain.product.repository;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.users.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignerProductRepository extends JpaRepository<DesignerProduct, Long> {
    List<DesignerProduct> findByProductStatus(ProductStatus productStatus);
    List<DesignerProduct> findByDesignerAndProductStatus(Designer designer, ProductStatus productStatus);
}
