package com.musinsa.domain.product.controller;

import com.musinsa.domain.product.dto.ProductDTO;
import com.musinsa.domain.product.dto.request.CreateProductRequest;
import com.musinsa.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.domain.product.dto.request.PatchProductRequest;
import com.musinsa.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<ProductDTO.Response> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO.Response> createProduct(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/list")
    public ResponseEntity<List<ProductDTO.Response>> createProducts(@Valid @RequestBody List<CreateProductRequest> requests) {
        return ResponseEntity.ok(productService.createProducts(requests));
    }

    @PatchMapping("")
    public ResponseEntity<ProductDTO.Response> patchProduct(@Valid @RequestBody PatchProductRequest request) {
        return ResponseEntity.ok(productService.patchProduct(request));
    }

    @PatchMapping("/list")
    public ResponseEntity<List<ProductDTO.Response>> patchProducts(@Valid @RequestBody List<PatchProductRequest> requests) {
        return ResponseEntity.ok(productService.patchProducts(requests));
    }

    @DeleteMapping("")
    public ResponseEntity<ProductDTO.Response> deleteProduct(@Valid @RequestBody DeleteProductRequest request) {
        return ResponseEntity.ok(productService.deleteProduct(request));
    }

    @DeleteMapping("/list")
    public ResponseEntity<List<ProductDTO.Response>> deleteProducts(@Valid @RequestBody List<DeleteProductRequest> requests) {
        return ResponseEntity.ok(productService.deleteProducts(requests));
    }
}
