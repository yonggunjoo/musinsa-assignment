package com.musinsa.domain.product.controller;

import com.musinsa.domain.product.dto.ProductDTO;
import com.musinsa.domain.product.dto.request.CreateProductRequest;
import com.musinsa.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.domain.product.dto.request.PatchProductRequest;
import com.musinsa.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "상품 컨트롤러", description = "상품 컨트롤러")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    @Operation(summary = "상품 목록 조회", description = "상품 목록 조회")
    public ResponseEntity<ProductDTO.Response> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("")
    @Operation(summary = "상품 단건 추가", description = "상품 단건 추가")
    public ResponseEntity<ProductDTO.Response> createProduct(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/list")
    @Operation(summary = "상품 목록 추가", description = "상품 목록 추가")
    public ResponseEntity<List<ProductDTO.Response>> createProducts(@Valid @RequestBody List<CreateProductRequest> requests) {
        return ResponseEntity.ok(productService.createProducts(requests));
    }

    @PatchMapping("")
    @Operation(summary = "상품 단건 수정", description = "상품 단건 수정")
    public ResponseEntity<ProductDTO.Response> patchProduct(@Valid @RequestBody PatchProductRequest request) {
        return ResponseEntity.ok(productService.patchProduct(request));
    }

    @PatchMapping("/list")
    @Operation(summary = "상품 단건 수정", description = "상품 단건 수정")
    public ResponseEntity<List<ProductDTO.Response>> patchProducts(@Valid @RequestBody List<PatchProductRequest> requests) {
        return ResponseEntity.ok(productService.patchProducts(requests));
    }

    @DeleteMapping("")
    @Operation(summary = "상품 단건 삭제", description = "상품 단건 삭제")
    public ResponseEntity<ProductDTO.Response> deleteProduct(@Valid @RequestBody DeleteProductRequest request) {
        return ResponseEntity.ok(productService.deleteProduct(request));
    }

    @DeleteMapping("/list")
    @Operation(summary = "상품 단건 삭제", description = "상품 단건 삭제")
    public ResponseEntity<List<ProductDTO.Response>> deleteProducts(@Valid @RequestBody List<DeleteProductRequest> requests) {
        return ResponseEntity.ok(productService.deleteProducts(requests));
    }
}
