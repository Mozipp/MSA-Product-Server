package com.mozipp.product.domain.product.repository;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerProductRepository extends JpaRepository<DesignerProduct, Long> {
}
