package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.report.dto.DesignerReportCreateDto;
import com.mozipp.product.domain.report.service.DesignerReportService;
import com.mozipp.product.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/report")
public class DesignerReportController {

    private final DesignerReportService designerReportService;

    // Designer 신고 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReport(@RequestBody DesignerReportCreateDto request, @AuthenticationPrincipal Long designerId) {
        designerReportService.createDesignerReport(request, designerId);
        return BaseResponse.success();
    }

}
