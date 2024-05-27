package com.musinsa.domain.category.service;

import com.musinsa.common.utils.DecimalFormatterUtil;
import com.musinsa.domain.category.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.category.dto.CategoryPriceSearchDTO;
import com.musinsa.domain.category.exception.CategoryErrorCode;
import com.musinsa.domain.category.repository.CategoryRepository;
import com.musinsa.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImplements implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryLowestPriceDTO.Response getCategoryLowestPriceBrand() {
        List<CategoryLowestPriceDTO.Projection> projectionList = categoryRepository.getCategoryLowestPriceBrand();

        List<CategoryLowestPriceDTO.CategoryInfo> categoryInfoList = projectionList.stream()
                .map(dto -> new CategoryLowestPriceDTO.CategoryInfo(dto.getCategoryName(), dto.getBrandName(), DecimalFormatterUtil.format(dto.getLowestPrice())))
                .collect(Collectors.toList());

        CategoryLowestPriceDTO.Response response = CategoryLowestPriceDTO.Response.builder()
                .categoryInfoList(categoryInfoList)
                .totalLowestPrice(DecimalFormatterUtil.format(projectionList.getFirst().getTotalLowestPrice()))
                .build();

        return response;
    }

    @Override
    public CategoryPriceSearchDTO.Response getCategoryPriceSearch(String categoryName) {

        List<CategoryPriceSearchDTO.Projection> lowestProjectionList = categoryRepository.getCategoryLowestPriceInfo(categoryName);
        List<CategoryPriceSearchDTO.Projection> highestProjectionList = categoryRepository.getCategoryHighestPriceInfo(categoryName);

        if (lowestProjectionList.isEmpty() || highestProjectionList.isEmpty()) {
            throw new RestApiException(CategoryErrorCode.NOT_FOUND);
        }

        List<CategoryPriceSearchDTO.BrandInfo> lowerstBrandInfoList = lowestProjectionList.stream()
                .map(dto -> new CategoryPriceSearchDTO.BrandInfo(dto.getBrandName(), DecimalFormatterUtil.format(dto.getLowestPrice())))
                .collect(Collectors.toList());

        List<CategoryPriceSearchDTO.BrandInfo> HighestBrandInfoList = highestProjectionList.stream()
                .map(dto -> new CategoryPriceSearchDTO.BrandInfo(dto.getBrandName(), DecimalFormatterUtil.format(dto.getHighestPrice())))
                .collect(Collectors.toList());

        CategoryPriceSearchDTO.Response response = CategoryPriceSearchDTO.Response.builder()
                .categoryName(categoryName)
                .lowestPriceInfo(lowerstBrandInfoList)
                .HighestPriceInfo(HighestBrandInfoList)
                .build();
        return response;
    }

}
