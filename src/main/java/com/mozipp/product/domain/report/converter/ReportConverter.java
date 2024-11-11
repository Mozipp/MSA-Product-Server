package com.mozipp.product.domain.report.converter;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.report.dto.DesignerReportCreateDto;
import com.mozipp.product.domain.report.dto.ModelReportCreateDto;
import com.mozipp.product.domain.report.entity.Report;
import com.mozipp.product.test.user.entity.User;

public class ReportConverter {

    public static Report toModelReport(ModelReportCreateDto request, User user, DesignerProduct designerProduct) {
        return Report.builder()
                .reportContent(request.getReportContent())
                .userId(user.getId())
                .targetId(designerProduct.getDesigner().getId())
                .designerProduct(designerProduct)
                .build();
    }

    public static Report toDesignerReport(DesignerReportCreateDto request, User user, DesignerProduct designerProduct, Long modelId) {
        return Report.builder()
                .reportContent(request.getReportContent())
                .userId(user.getId())
                .targetId(modelId)
                .designerProduct(designerProduct)
                .build();
    }
}
