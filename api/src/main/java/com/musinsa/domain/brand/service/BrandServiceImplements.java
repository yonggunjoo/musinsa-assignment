package com.musinsa.domain.brand.service;

import com.musinsa.common.utils.DecimalFormatterUtil;
import com.musinsa.domain.brand.dto.BrandLowestPriceDTO;
import com.musinsa.domain.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImplements implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandLowestPriceDTO.Response getSingleBrandCategoryLowestPrice() {
        List<BrandLowestPriceDTO.Projection> lowestPriceProjection = brandRepository.getSingleBrandCategoryLowestPrice();

        List<BrandLowestPriceDTO.CategoryInfo> categoryInfo = lowestPriceProjection.stream()
                .map(projection -> new BrandLowestPriceDTO.CategoryInfo(projection.getCategoryName(), DecimalFormatterUtil.format(projection.getCategoryPrice())))
                .collect(Collectors.toList());

        BrandLowestPriceDTO.Body body = BrandLowestPriceDTO.Body.builder()
                .totalPrice(DecimalFormatterUtil.format(lowestPriceProjection.getFirst().getTotalPrice()))
                .categoryInfos(categoryInfo)
                .brandName(lowestPriceProjection.getFirst().getBrandName())
                .build();

        BrandLowestPriceDTO.Response response = BrandLowestPriceDTO.Response.builder()
                .body(body)
                .build();

        return response;
    }

}
