package com.musinsa.domain.brand.service;


import com.musinsa.common.entity.Brand;
import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.brand.dto.request.CreateBrandRequest;
import com.musinsa.domain.brand.dto.request.DeleteBrandRequest;
import com.musinsa.domain.brand.dto.request.PatchBrandRequest;

import java.util.List;

public interface BrandService {
    BrandDTO.Response getBrands();

    BrandDTO.Response createBrand(CreateBrandRequest request);

    List<BrandDTO.Response> createBrands(List<CreateBrandRequest> request);

    BrandDTO.Response patchBrand(PatchBrandRequest request);

    List<BrandDTO.Response> patchBrands(List<PatchBrandRequest> request);

    BrandDTO.Response deleteBrand(DeleteBrandRequest request);

    List<BrandDTO.Response> deleteBrands(List<DeleteBrandRequest> request);

    boolean existsByName(String name);

    Brand findByName(String name);
}
