package com.musinsa.domain.price.service;

import com.musinsa.common.utils.DecimalFormatterUtil;
import com.musinsa.domain.price.dto.BrandLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryPriceSearchDTO;
import com.musinsa.domain.price.exception.PriceErrorCode;
import com.musinsa.domain.price.repository.PriceRepository;
import com.musinsa.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceServiceImplements implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public CategoryLowestPriceDTO.Response getCategoryLowestPriceBrand() {
        List<CategoryLowestPriceDTO.Projection> projectionList = priceRepository.getCategoryLowestPriceBrand();

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
    public BrandLowestPriceDTO.Response getSingleBrandCategoryLowestPrice() {
        List<BrandLowestPriceDTO.Projection> lowestPriceProjection = priceRepository.getSingleBrandCategoryLowestPrice();

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

    @Override
    public CategoryPriceSearchDTO.Response getCategoryPriceSearch(String categoryName) {

        List<CategoryPriceSearchDTO.Projection> lowestProjectionList = priceRepository.getCategoryLowestPriceInfo(categoryName);
        List<CategoryPriceSearchDTO.Projection> highestProjectionList = priceRepository.getCategoryHighestPriceInfo(categoryName);

        if (lowestProjectionList.isEmpty() || highestProjectionList.isEmpty()) {
            throw new RestApiException(PriceErrorCode.NOT_FOUND);
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
