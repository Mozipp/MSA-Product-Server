package com.mozipp.product.domain.product.service;

import com.mozipp.product.domain.product.converter.DesignerProductConverter;
import com.mozipp.product.domain.product.dto.DesignerProductCreateDto;
import com.mozipp.product.domain.product.dto.DesignerProductResponse;
import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerProductService {

    private final DesignerProductRepository designerProductRepository;

    public List<DesignerProductResponse> getDesignerProducts() {
        List<DesignerProduct> designerProducts = designerProductRepository.findAll();
        return DesignerProductConverter.toDesignerProductResponse(designerProducts);
    }

    @Transactional
    public void createDesignerProduct(User user, DesignerProductCreateDto request) {
        Designer designer = (Designer) user;
        DesignerProduct designerProduct = DesignerProductConverter.toDesignerProductCreateDto(request);
        designer.addProduct(designerProduct);
        designerProductRepository.save(designerProduct);
    }
}
