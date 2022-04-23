package com.graphicstore.product.domain.service;

import com.graphicstore.product.api.exception.ProductNotFoundException;
import com.graphicstore.product.domain.service.ProductService;
import com.graphicstore.product.domain.service.dto.ProductRequest;
import com.graphicstore.product.domain.service.dto.ProductResponse;
import com.graphicstore.product.domain.service.mapper.ProductMapper;
import com.graphicstore.product.domain.agregate.enums.ColorBySide;
import com.graphicstore.product.domain.agregate.entity.Product;
import com.graphicstore.product.domain.agregate.enums.ProductCategory;
import com.graphicstore.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService underTest;

    private ProductRequest request;
    private Product product;
    private ProductResponse response;

    @BeforeEach
    void setUp() {
        this.request = new ProductRequest(
                "Cartão de visita",
                ProductCategory.OFF_SET,
                "Couchê 90g",
                ColorBySide._4x4,
                5, 9,
                List.of("verniz total"),
                1000,
                new BigDecimal("90.00"));

        this.product = new Product(
                1,
                "Cartão de visita",
                ProductCategory.OFF_SET,
                "Couchê 90g",
                ColorBySide._4x4,
                5, 9,
                List.of("verniz total"),
                1000,
                new BigDecimal("90.00"));

        this. response = new ProductResponse(
                1,
                "Cartão de visita",
                ProductCategory.OFF_SET,
                "Couchê 90g",
                ColorBySide._4x4,
                5, 9,
                List.of("verniz total"),
                1000,
                new BigDecimal("90.00"),
                LocalDateTime.now());
    }

    @Test
    @DisplayName("Should call repository to save a product")
    void save() {
        //given
        //when
        when(mapper.toProduct(request)).thenReturn(product);
        when(mapper.toResponse(product)).thenReturn(response);
        when(repository.save(product)).thenReturn(product);

        var result = underTest.save(request);

        //then
        assertThat(result).isEqualTo(response);
        verify(repository, times(1)).save(product);
        verify(mapper, times(1)).toResponse(product);
        verify(mapper, times(1)).toProduct(request);

    }

    @Test
    @DisplayName("Should call the repository findbyid")
    void findById() throws ProductNotFoundException {
        //given
        var id = 1;

        //when
        when(mapper.toResponse(product)).thenReturn(response);
        when(repository.findById(id)).thenReturn(Optional.of(product));

        var result = underTest.findById(id);

        //then
        assertThat(result).isEqualTo(response);
        verify(repository, times(1)).findById(1);
        verify(mapper, times(1)).toResponse(product);
    }

    @Test
    @DisplayName("Should throw error when trying to call repository.findbyid with an unregistered id")
    void findById2() {
        //given
        Integer id = 1;

        //when
        when(repository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.findById(id)).isInstanceOf(ProductNotFoundException.class)
                        .hasMessageContaining(String.format("Product with id %s not found", id));
        verify(repository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should call repository.findall method.")
    void findAll() throws ProductNotFoundException {

        //given

        //when
        when(mapper.toResponseList(List.of(product))).thenReturn(List.of(response));
        when(repository.findAll()).thenReturn(List.of(product));

        var result = underTest.findAll();

        //then
        assertThat(result).isEqualTo(List.of(response));
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toResponseList(List.of(product));

    }

    @Test
    @DisplayName("Should throw error when call findall and repository is empity.")
    void findAll2() {
        //given
        //when
        when(repository.findAll()).thenReturn(List.of());

        //then
        assertThatThrownBy(() -> underTest.findAll()).isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("No products registerd.");
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should do nothing when call delete from repository.")
    void delete() throws ProductNotFoundException {
        //given
        Integer id = 1;
        //when
        when(repository.findById(id)).thenReturn(Optional.of(product));
        underTest.deleteById(id);
        //then
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should throw error when call delete from repository.")
    void delete2() throws ProductNotFoundException {
        //given
        Integer id = 1;
        //when
        when(repository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.deleteById(id)).isInstanceOf(ProductNotFoundException.class)
                        .hasMessageContaining(String.format("No product with id: %d registered.", id));
        verify(repository, times(1)).findById(id);
    }

}