package com.graphicstore.product.domain.service;

import com.graphicstore.product.api.exception.ProductNotFoundException;
import com.graphicstore.product.domain.agregate.entity.Product;
import com.graphicstore.product.domain.repository.ProductRepository;
import com.graphicstore.product.domain.service.dto.ProductRequest;
import com.graphicstore.product.domain.service.dto.ProductResponse;
import com.graphicstore.product.domain.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ProductResponse> findAll() throws ProductNotFoundException {
        List<ProductResponse> responses = productMapper.toResponseList(repository.findAll());
        if(responses.isEmpty()){
            throw new ProductNotFoundException("No products registerd.");
        }
        return responses;
    }

    public void deleteById(Integer id) throws ProductNotFoundException {
        if(repository.findById(id).isEmpty()){
            throw new ProductNotFoundException(
                    String.format("No product with id: %d registered.", id));
        }
        repository.deleteById(id);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
