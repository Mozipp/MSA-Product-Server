package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.report.dto.ModelReportCreateDto;
import com.mozipp.product.domain.report.service.ModelReportService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/report")
public class ModelReportController {

    private final ModelReportService modelReportService;

    // Model 신고 등록
    @PostMapping
    public BaseResponse<Object> createModelReport(@RequestBody ModelReportCreateDto request, @AuthenticationPrincipal Long modelId) {
        modelReportService.createModelReport(request, modelId);
        return BaseResponse.success();
    }
}
