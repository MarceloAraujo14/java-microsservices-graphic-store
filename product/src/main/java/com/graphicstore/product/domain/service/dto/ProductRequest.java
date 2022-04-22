package com.graphicstore.product.domain.service.dto;

import com.graphicstore.product.domain.agregate.enums.ColorBySide;
import com.graphicstore.product.domain.agregate.enums.ProductCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public record ProductRequest(String name,
                             ProductCategory category,
                             String substrate,
                             ColorBySide color,
                             Integer height,
                             Integer width,
                             List<String> finishings,
                             Integer quantity,
                             BigDecimal price) implements Serializable {
}
