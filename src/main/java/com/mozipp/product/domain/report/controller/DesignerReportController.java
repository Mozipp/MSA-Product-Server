package com.mozipp.product.domain.report.controller;

import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.domain.report.dto.DesignerReportCreateDto;
import com.mozipp.product.domain.report.service.DesignerReportService;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponse;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Designer;
import com.mozipp.product.users.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/designer/report")
public class DesignerReportController {

    private final DesignerReportService designerReportService;
    private final UserFindService userFindService;

    // Designer 신고 등록
    @PostMapping
    public BaseResponse<Object> createDesignerReport(@RequestBody DesignerReportCreateDto request, @RequestHeader("Authorization") String authorizationHeader) {
        Long designerId = userFindService.getUserId(authorizationHeader);
        designerReportService.createDesignerReport(request, designerId);
        return BaseResponse.success();
    }

}
