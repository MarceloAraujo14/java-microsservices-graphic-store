package com.graphicstore.product.domain.service.mapper;

import com.graphicstore.product.domain.service.dto.ProductRequest;
import com.graphicstore.product.domain.agregate.enums.ColorBySide;
import com.graphicstore.product.domain.agregate.entity.Product;
import com.graphicstore.product.domain.agregate.enums.ProductCategory;
import com.graphicstore.product.domain.service.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = "test")
@SpringBootTest
record ProductMapperTest(ProductMapper productMapper) {

    @Autowired
    ProductMapperTest {
    }

    @Test
    void toProduct() {
        //given
        ProductRequest dto = new ProductRequest(
                "Cartão de visita",
                ProductCategory.OFF_SET,
                "Couchê 90g",
                ColorBySide._4x4,
                5, 9,
                List.of("verniz total"),
                1000,
                new BigDecimal("90.00"));

        Product product = new Product(
                1,
                "Cartão de visita",
                ProductCategory.OFF_SET,
                "Couchê 90g",
                ColorBySide._4x4,
                5, 9,
                List.of("verniz total"),
                1000,
                new BigDecimal("90.00"));
        //when
        Product result = productMapper.toProduct(dto);
        result.setId(1);

        //then
        assertThat(result).isEqualTo(product);
    }

}