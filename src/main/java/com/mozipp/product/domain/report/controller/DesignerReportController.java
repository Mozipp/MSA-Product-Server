package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.report.dto.DesignerReportCreateDto;
import com.mozipp.product.domain.report.service.DesignerReportService;
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
@RequestMapping("/api/products/designer/report")
public class DesignerReportController {

    private final DesignerReportService designerReportService;
    private final UserFindService userFindService;

    // Designer 신고 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReport(@RequestBody DesignerReportCreateDto request, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        designerReportService.createDesignerReport(request, user);
        return BaseResponse.success();
    }

}
