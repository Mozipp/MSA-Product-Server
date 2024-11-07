package com.mozipp.product.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerProductRequest {
    private String title;
    private String description;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
}
