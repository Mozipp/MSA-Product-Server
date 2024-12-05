package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.domain.report.dto.ModelReportCreateDto;
import com.mozipp.product.domain.report.service.ModelReportService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/report")
public class ModelReportController {

    private final ModelReportService modelReportService;
    private final UserFindService userFindService;

    // Model 신고 등록
    @PostMapping
    public BaseResponse<Object> createModelReport(@RequestBody ModelReportCreateDto request, @AuthenticationPrincipal Long modelId) {
        modelReportService.createModelReport(request, modelId);
        return BaseResponse.success();
    }
}
