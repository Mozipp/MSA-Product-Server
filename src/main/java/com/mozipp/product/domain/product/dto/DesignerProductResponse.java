package com.mozipp.product.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerProductResponse {

    private Long designerProductId;
    private String title;
    private String description;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    public DesignerProductResponse(Long designerProductId, String title, String description, String design, String modelPreferDescription, String preferBreed, LocalDateTime createdAt) {
        this.designerProductId = designerProductId;
        this.title = title;
        this.description = description;
        this.design = design;
        this.modelPreferDescription = modelPreferDescription;
        this.preferBreed = preferBreed;
        this.createdAt = createdAt;
    }
}
