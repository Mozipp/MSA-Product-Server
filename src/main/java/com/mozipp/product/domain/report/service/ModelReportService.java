package com.mozipp.product.domain.report.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.report.converter.ReportConverter;
import com.mozipp.product.domain.report.dto.ModelReportCreateDto;
import com.mozipp.product.domain.report.entity.Report;
import com.mozipp.product.domain.report.repository.ReportRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.model.entity.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModelReportService {

    private final ReportRepository reportRepository;
    private final DesignerProductRepository designerProductRepository;

    @Transactional
    public void createModelReport(ModelReportCreateDto request, Model model) {
        DesignerProduct designerProduct = designerProductRepository.findById(request.getDesignerProductId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));
        Report report = ReportConverter.toModelReport(request, model, designerProduct);
        reportRepository.save(report);
    }
}
