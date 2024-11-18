package com.mozipp.product.domain.report.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerReportCreateDto {
    private Long designerId;
    private Long designerProductId;
    private String reportContent;
}
