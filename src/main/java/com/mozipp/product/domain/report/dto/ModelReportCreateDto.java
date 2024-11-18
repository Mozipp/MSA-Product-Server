package com.mozipp.product.domain.report.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelReportCreateDto {
    private Long modelId;
    private Long designerProductId;
    private String reportContent;
}
