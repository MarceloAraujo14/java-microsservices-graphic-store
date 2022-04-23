package com.graphicstore.product.api.controller;

import com.graphicstore.product.api.exception.ProductNotFoundException;
import com.graphicstore.product.domain.service.ProductService;
import com.graphicstore.product.domain.service.dto.ProductRequest;
import com.graphicstore.product.domain.service.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/graphic-store/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a product based on the information provided in the body request.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product registered with sucess.",
                    content = { @Content(mediaType = "application/json", schema =
                                @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid information provided",
                    content = @Content)    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request){
        ProductResponse response = productService.save(request);
        URI location = URI.create(String.format("/product/%s", response.id()));
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Find a product by id provided on url path")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found.",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found.",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid information provided",
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.findById(id));
    }

    @Operation(summary = "Find a list of all products registered.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product list found.",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found.",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid information provided",
                    content = @Content)})
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> findAll() throws ProductNotFoundException {
        return ResponseEntity.ok(productService.findAll());
    }

}
