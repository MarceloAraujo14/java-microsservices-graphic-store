package com.graphicstore.product.domain.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.graphicstore.product.domain.agregate.enums.ColorBySide;
import com.graphicstore.product.domain.agregate.enums.ProductCategory;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record ProductResponse(Integer id, String name,
                              ProductCategory category,
                              String substrate,
                              ColorBySide color,
                              Integer height,
                              Integer width,
                              List<String> finishings,
                              Integer quantity,
                              BigDecimal price,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
                              @DateTimeFormat
                              @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                              @JsonSerialize(using = LocalDateTimeSerializer.class)
                              LocalDateTime createdAt) {
}
