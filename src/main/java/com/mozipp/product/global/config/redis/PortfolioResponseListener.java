package com.mozipp.product.global.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozipp.product.domain.product.service.DesignerProductService;
import org.springframework.stereotype.Service;

@Service
public class PortfolioResponseListener {
    private final DesignerProductService designerProductService;
    private final ObjectMapper objectMapper;

    public PortfolioResponseListener(DesignerProductService designerProductService, ObjectMapper objectMapper) {
        this.designerProductService = designerProductService;
        this.objectMapper = objectMapper;
    }

    public void handlePortfolioCreationSuccess(String message) {
        try {
            PortfolioResultEvent event = objectMapper.readValue(message, PortfolioResultEvent.class);
            designerProductService.handlePortfolioCreated(event.getProductId());
        } catch (Exception e) {
            // 로깅 처리
            // 예: logger.error("Failed to handle PortfolioCreationSuccess", e);
        }
    }

    public void handlePortfolioCreationFail(String message) {
        try {
            PortfolioResultEvent event = objectMapper.readValue(message, PortfolioResultEvent.class);
            designerProductService.handlePortfolioCreationFailed(event.getProductId());
        } catch (Exception e) {
            // 로깅 처리
            // 예: logger.error("Failed to handle PortfolioCreationFail", e);
        }
    }
}