package com.graphicstore.product.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.graphicstore.product.api.exception.ProductNotFoundException;
import com.graphicstore.product.domain.agregate.entity.Product;
import com.graphicstore.product.domain.repository.ProductRepository;
import com.graphicstore.product.domain.service.dto.ProductRequest;
import com.graphicstore.product.domain.service.dto.ProductResponse;
import com.graphicstore.product.domain.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    private ProductRepository repository;

    public ProductResponse save(ProductRequest request){

        return productMapper.toResponse(
                repository.save(
                        productMapper.toProduct(request)));
    }

    public ProductResponse findById(Integer id) throws ProductNotFoundException {
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException(
                    String.format("Product with id %s not found", id));
        }
        return productMapper.toResponse(product.get());
    }

    public List<Product> findAll() throws ProductNotFoundException {
        List<Product> products = repository.findAll();
        if(products.isEmpty()){
            throw new ProductNotFoundException(
                    String.format("No products registerd."));
        }
        return products;
    }

}
