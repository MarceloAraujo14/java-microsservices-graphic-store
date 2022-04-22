package com.graphicstore.product.domain.repository;

import com.graphicstore.product.domain.agregate.enums.ColorBySide;
import com.graphicstore.product.domain.agregate.entity.Product;
import com.graphicstore.product.domain.agregate.enums.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
record ProductRepositoryTest(ProductRepository underTest) {

    @Autowired
    ProductRepositoryTest {
    }

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void save() {
        //given
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
        underTest.save(product);

        Product result = underTest.getById(1);

        //then
        assertThat(result).isEqualTo(product);
    }


    @Test
    void findProductNameById() {
        //given
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
        underTest.save(product);
        String expected = "Cartão de visita";
        String result = underTest.findProductNameById(1);

        //then
        assertThat(result).isEqualTo(expected);
    }
}