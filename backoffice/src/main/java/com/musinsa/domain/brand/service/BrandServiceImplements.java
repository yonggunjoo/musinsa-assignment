package com.musinsa.domain.brand.service;

import com.musinsa.common.entity.Brand;
import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.brand.dto.request.CreateBrandRequest;
import com.musinsa.domain.brand.dto.request.DeleteBrandRequest;
import com.musinsa.domain.brand.dto.request.PatchBrandRequest;
import com.musinsa.domain.brand.exception.BrandErrorCode;
import com.musinsa.domain.brand.repository.BrandRepository;
import com.musinsa.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BrandServiceImplements implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandDTO.Response getBrands() {
        List<Brand> brand = brandRepository.findAll();
        List<BrandDTO> brandDTOS = brand.stream().map(BrandDTO::convertToBrandDTO).collect(Collectors.toList());
        return BrandDTO.Response.builder()
                .brands(brandDTOS)
                .build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public BrandDTO.Response createBrand(CreateBrandRequest request) {
        boolean existsedByName = this.existsByName(request.getBrandName());
        if (existsedByName) {
            throw new RestApiException(BrandErrorCode.ALREADY_EXISTS);
        }

        Brand brand = Brand.builder().name(request.getBrandName()).build();
        brand = brandRepository.save(brand);

        return BrandDTO.Response.builder()
                .brand(BrandDTO.convertToBrandDTO(brand))
                .build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<BrandDTO.Response> createBrands(List<CreateBrandRequest> requests) {
        List<BrandDTO.Response> responses = new ArrayList<>();

        for (CreateBrandRequest request : requests) {
            responses.add(createBrand(request));
        }

        return responses;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public BrandDTO.Response patchBrand(PatchBrandRequest request) {
        boolean existsedById = this.existsById(request.getId());
        if (!existsedById) {
            throw new RestApiException(BrandErrorCode.NOT_REGISTRED);
        }

        boolean existsedByName = this.existsByName(request.getBrandName());
        if (existsedByName) {
            throw new RestApiException(BrandErrorCode.DUPLICATE);
        }

        Brand brand = Brand.builder().id(request.getId()).name(request.getBrandName()).build();
        brand = brandRepository.save(brand);

        return BrandDTO.Response.builder()
                .brand(BrandDTO.convertToBrandDTO(brand))
                .build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<BrandDTO.Response> patchBrands(List<PatchBrandRequest> requests) {
        List<BrandDTO.Response> responses = new ArrayList<>();

        for (PatchBrandRequest request : requests) {
            responses.add(patchBrand(request));
        }

        return responses;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public BrandDTO.Response deleteBrand(DeleteBrandRequest request) {
        Brand brand = this.findBrandById(request.getId());
        BrandDTO brandDTO = BrandDTO.convertToBrandDTO(brand);

        brandRepository.deleteById(request.getId());

        return BrandDTO.Response.builder().brand(brandDTO).build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<BrandDTO.Response> deleteBrands(List<DeleteBrandRequest> requests) {
        List<BrandDTO.Response> responses = new ArrayList<>();

        for (DeleteBrandRequest request : requests) {
            responses.add(deleteBrand(request));
        }

        return responses;
    }

    public boolean existsById(Long id) {
        return brandRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }

    @Override
    public Brand findByName(String name) {
        return brandRepository.findByName(name);
    }

    public Brand findBrandById(Long productId) {
        return brandRepository.findById(productId)
                .orElseThrow(() -> new RestApiException(BrandErrorCode.NOT_REGISTRED));
    }

}
