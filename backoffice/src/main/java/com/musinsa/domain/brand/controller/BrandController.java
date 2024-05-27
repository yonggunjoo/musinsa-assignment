package com.musinsa.domain.brand.controller;

import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.brand.dto.request.CreateBrandRequest;
import com.musinsa.domain.brand.dto.request.DeleteBrandRequest;
import com.musinsa.domain.brand.dto.request.PatchBrandRequest;
import com.musinsa.domain.brand.service.BrandService;
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
public class BrandController {
    private final BrandService brandService;

    @GetMapping("")
    public ResponseEntity<BrandDTO.Response> getProducts() {
        return ResponseEntity.ok(brandService.getBrands());
    }

    @PostMapping
    public ResponseEntity<BrandDTO.Response> createBrand(@Valid @RequestBody CreateBrandRequest request) {
        return ResponseEntity.ok(brandService.createBrand(request));
    }

    @PostMapping("/list")
    public ResponseEntity<List<BrandDTO.Response>> createBrand(@Valid @RequestBody List<CreateBrandRequest> requests) {
        return ResponseEntity.ok(brandService.createBrands(requests));
    }

    @PatchMapping
    public ResponseEntity<BrandDTO.Response> patchBrand(@Valid @RequestBody PatchBrandRequest request) {
        return ResponseEntity.ok(brandService.patchBrand(request));
    }

    @PatchMapping("/list")
    public ResponseEntity<List<BrandDTO.Response>> patchBrands(@Valid @RequestBody List<PatchBrandRequest> requests) {
        return ResponseEntity.ok(brandService.patchBrands(requests));
    }

    @DeleteMapping
    public ResponseEntity<BrandDTO.Response> deleteBrand(@Valid @RequestBody DeleteBrandRequest request) {
        return ResponseEntity.ok(brandService.deleteBrand(request));
    }

    @DeleteMapping("/list")
    public ResponseEntity<List<BrandDTO.Response>> deleteBrands(@Valid @RequestBody List<DeleteBrandRequest> requests) {
        return ResponseEntity.ok(brandService.deleteBrands(requests));
    }
}
