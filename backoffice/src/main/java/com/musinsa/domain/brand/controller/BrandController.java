package com.musinsa.domain.brand.controller;

import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.brand.dto.request.CreateBrandRequest;
import com.musinsa.domain.brand.dto.request.DeleteBrandRequest;
import com.musinsa.domain.brand.dto.request.PatchBrandRequest;
import com.musinsa.domain.brand.service.BrandService;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
@Validated
@Tag(name = "브랜드 컨트롤러", description = "브랜드 컨트롤러")
public class BrandController {
    private final BrandService brandService;

    @Operation(summary = "브랜드 목록 조회", description = "브랜드 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<BrandDTO.Response> getProducts() {
        return ResponseEntity.ok(brandService.getBrands());
    }

    @Operation(summary = "브랜드 단건 추가", description = "브랜드 단건 추가")
    @PostMapping
    public ResponseEntity<BrandDTO.Response> createBrand(@Valid @RequestBody CreateBrandRequest request) {
        return ResponseEntity.ok(brandService.createBrand(request));
    }

    @PostMapping("/list")
    @Operation(summary = "브랜드 목록 추가", description = "브랜드 목록 추가")
    public ResponseEntity<List<BrandDTO.Response>> createBrand(@Valid @RequestBody List<CreateBrandRequest> requests) {
        return ResponseEntity.ok(brandService.createBrands(requests));
    }

    @PatchMapping
    @Operation(summary = "브랜드 단건 수정", description = "브랜드 단건 수정")
    public ResponseEntity<BrandDTO.Response> patchBrand(@Valid @RequestBody PatchBrandRequest request) {
        return ResponseEntity.ok(brandService.patchBrand(request));
    }

    @PatchMapping("/list")
    @Operation(summary = "브랜드 목록 수정", description = "브랜드 목록 수정")
    public ResponseEntity<List<BrandDTO.Response>> patchBrands(@Valid @RequestBody List<PatchBrandRequest> requests) {
        return ResponseEntity.ok(brandService.patchBrands(requests));
    }

    @DeleteMapping
    @Operation(summary = "브랜드 단건 삭제", description = "브랜드 단건 삭제")
    public ResponseEntity<BrandDTO.Response> deleteBrand(@Valid @RequestBody DeleteBrandRequest request) {
        return ResponseEntity.ok(brandService.deleteBrand(request));
    }

    @DeleteMapping("/list")
    @Operation(summary = "브랜드 목록 삭제", description = "브랜드 목록 삭제")
    public ResponseEntity<List<BrandDTO.Response>> deleteBrands(@Valid @RequestBody List<DeleteBrandRequest> requests) {
        return ResponseEntity.ok(brandService.deleteBrands(requests));
    }
}
