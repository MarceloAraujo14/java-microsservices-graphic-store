package com.graphicstore.product.domain.service.mapper;

import com.graphicstore.product.domain.service.dto.ProductRequest;
import com.graphicstore.product.domain.agregate.entity.Product;
import com.graphicstore.product.domain.service.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Mapper(componentModel = "spring")
public interface ProductMapper {

    public ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toProduct(ProductRequest request);

    ProductRequest toRequest(Product product);

    List<ProductRequest> toRquestList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    List<Product> toProductList(List<ProductRequest> requests);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ProductResponse toResponse(Product product);
}
