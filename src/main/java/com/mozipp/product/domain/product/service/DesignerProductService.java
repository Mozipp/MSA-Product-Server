package com.mozipp.product.domain.product.service;

import com.mozipp.product.domain.product.converter.ProductConverter;
import com.mozipp.product.domain.product.dto.DesignerProductResponse;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerProductService {

    private final DesignerProductRepository designerProductRepository;

    public List<DesignerProductResponse> getDesignerProducts() {
        List<DesignerProduct> designerProducts = designerProductRepository.findAll();
        return ProductConverter.toDesignerProductResponse(designerProducts);
    }
}
