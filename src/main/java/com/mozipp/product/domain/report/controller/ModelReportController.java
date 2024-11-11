package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.report.dto.ModelReportCreateDto;
import com.mozipp.product.domain.report.service.ModelReportService;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.test.user.entity.User;
import com.mozipp.product.test.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model/report")
public class ModelReportController {

    private final ModelReportService modelReportService;
    private final UserFindService userFindService;

    // Model 신고 등록
    @PostMapping
    public BaseResponse<Object> createModelReport(@RequestBody ModelReportCreateDto request, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        modelReportService.createModelReport(request, user);
        return BaseResponse.success();
    }
}
