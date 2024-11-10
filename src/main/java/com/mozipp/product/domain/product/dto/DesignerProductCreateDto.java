package com.mozipp.product.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerProductCreateDto {
    private String title;
    private String introduction;
    private String design;
    private String modelPreferDescription;
    private String preferBreed;
}
