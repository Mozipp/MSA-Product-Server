package com.mozipp.product.domain.report.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.report.dto.DesignerReportCreateDto;
import com.mozipp.product.domain.report.dto.ModelReportCreateDto;
import com.mozipp.product.domain.report.entity.Report;

public class ReportConverter {

    public static Report toModelReport(ModelReportCreateDto request, Long modelId, DesignerProduct designerProduct, Long designerId) {
        return Report.builder()
                .reportContent(request.getReportContent())
                .userId(modelId)
                .targetId(designerId)
                .designerProduct(designerProduct)
                .build();
    }

    public static Report toDesignerReport(DesignerReportCreateDto request, Long designerId, DesignerProduct designerProduct, Long modelId) {
        return Report.builder()
                .reportContent(request.getReportContent())
                .userId(designerId)
                .targetId(modelId)
                .designerProduct(designerProduct)
                .build();
    }
}
