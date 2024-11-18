package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.report.dto.DesignerReportCreateDto;
import com.mozipp.product.domain.report.service.DesignerReportService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.test.designer.entity.Designer;
import com.mozipp.product.test.designer.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/report")
public class DesignerReportController {

    private final DesignerReportService designerReportService;
    private final DesignerRepository designerRepository;

    // Designer 신고 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReport(@RequestBody DesignerReportCreateDto request) {
        Designer designer = designerRepository.findById(request.getDesignerId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND));
        designerReportService.createDesignerReport(request, designer);
        return BaseResponse.success();
    }

}
