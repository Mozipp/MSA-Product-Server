package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.report.dto.ModelReportCreateDto;
import com.mozipp.product.domain.report.service.ModelReportService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/model/report")
public class ModelReportController {

    private final ModelReportService modelReportService;
    private final ModelRepository modelRepository;

    // Model 신고 등록
    @PostMapping
    public BaseResponse<Object> createModelReport(@RequestBody ModelReportCreateDto request) {
        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        modelReportService.createModelReport(request, model);
        return BaseResponse.success();
    }
}
