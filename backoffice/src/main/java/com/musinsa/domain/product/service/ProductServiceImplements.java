package com.musinsa.domain.product.service;

import com.musinsa.entity.Brand;
import com.musinsa.entity.Category;
import com.musinsa.entity.Product;
import com.musinsa.domain.brand.exception.BrandErrorCode;
import com.musinsa.domain.brand.service.BrandService;
import com.musinsa.domain.category.exception.CategoryErrorCode;
import com.musinsa.domain.category.service.CategoryService;
import com.musinsa.domain.product.dto.ProductDTO;
import com.musinsa.domain.product.dto.request.CreateProductRequest;
import com.musinsa.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.domain.product.dto.request.PatchProductRequest;
import com.musinsa.domain.product.exception.ProductErrorCode;
import com.musinsa.domain.product.repository.ProductRepository;
import com.musinsa.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImplements implements ProductService {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Override
    public ProductDTO.Response getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream().map(ProductDTO::convertToProductDTO).collect(Collectors.toList());
        return ProductDTO.Response.builder()
                .products(productDTOs)
                .build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ProductDTO.Response createProduct(CreateProductRequest request) {
        Brand brand = null;
        boolean existsedByBrandName = brandService.existsByName(request.getBrandName());

        if (!existsedByBrandName) {
            throw new RestApiException(BrandErrorCode.NOT_REGISTRED);
        } else {
            brand = brandService.findByName(request.getBrandName());
            if (Objects.isNull(brand)) {
                throw new RestApiException(BrandErrorCode.NOT_FOUNT);
            }
        }

        boolean existsedByCategoryName = categoryService.existsByName(request.getCategoryName());
        if (!existsedByCategoryName) {
            throw new RestApiException(CategoryErrorCode.NOT_REGISTRED);
        }

        Category category = categoryService.findByName(request.getCategoryName());
        if (Objects.isNull(category)) {
            throw new RestApiException(CategoryErrorCode.NOT_FOUNT);
        }

        Product product = Product.builder()
                .brand(brand)
                .category(category)
                .price(request.getPrice())
                .build();

        // 중복 등록 상품 확인
        boolean existsProduct = productRepository.existsByBrandAndCategory(brand, category);
        if (existsProduct) {
            throw new RestApiException(ProductErrorCode.ALREADY_EXISTS);
        } else {
            product = productRepository.save(product);
        }

        return ProductDTO.Response.builder()
                .product(ProductDTO.convertToProductDTO(product))
                .build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<ProductDTO.Response> createProducts(List<CreateProductRequest> requests) {
        List<ProductDTO.Response> responses = new ArrayList<>();

        for (CreateProductRequest request : requests) {
            responses.add(createProduct(request));
        }

        return responses;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ProductDTO.Response patchProduct(PatchProductRequest request) {
        Product product = null;
        Brand brand = null;
        Category category = null;

        boolean existsedByProduct = this.existsById(request.getId());
        if (!existsedByProduct) {
            throw new RestApiException(ProductErrorCode.NOT_REGISTRED);
        }

        product = this.findProductById(request.getId());

        if (Objects.nonNull(request.getPrice()) && request.getPrice().compareTo(BigInteger.ZERO) <= 0) {
            throw new RestApiException(ProductErrorCode.PRICE_BAD_REQUEST);
        }

        // 브랜드 정보 요청은 필수가 아닐 수 있기 떄문에 브랜드 정보가 없는경우 등록된 상품의 브랜드 조회
        if (Objects.nonNull(request.getBrandName()) && Strings.isNotEmpty(request.getBrandName().trim())) {
            boolean existsedByBrandName = brandService.existsByName(request.getBrandName());
            if (!existsedByBrandName) {
                throw new RestApiException(BrandErrorCode.NOT_REGISTRED);
            }

            brand = brandService.findByName(request.getBrandName());
            if (Objects.isNull(brand)) {
                throw new RestApiException(BrandErrorCode.NOT_FOUNT);
            }
        } else {
            brand = product.getBrand();
        }

        // 카테고리 정보 요청은 필수가 아닐 수 있기 떄문에 브랜드 정보가 없는경우 등록된 상품의 브랜드 조회
        if (Objects.nonNull(request.getCategoryName()) && Strings.isNotEmpty(request.getCategoryName().trim())) {
            boolean existsedByCategoryName = categoryService.existsByName(request.getCategoryName());
            if (!existsedByCategoryName) {
                throw new RestApiException(CategoryErrorCode.NOT_REGISTRED);
            }

            category = categoryService.findByName(request.getCategoryName());
            if (Objects.isNull(category)) {
                throw new RestApiException(CategoryErrorCode.NOT_FOUNT);
            }
        } else {
            category = product.getCategory();
        }

        product = Product.builder()
                .id(request.getId())
                .brand(brand)
                .category(category)
                .price(Objects.isNull(request.getPrice()) ? product.getPrice() : request.getPrice())
                .build();

        product = productRepository.save(product);

        ProductDTO productDTO = ProductDTO.convertToProductDTO(product);

        return ProductDTO.Response.builder()
                .product(productDTO)
                .build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<ProductDTO.Response> patchProducts(List<PatchProductRequest> requests) {
        List<ProductDTO.Response> responses = new ArrayList<>();

        for (PatchProductRequest request : requests) {
            responses.add(patchProduct(request));
        }

        return responses;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ProductDTO.Response deleteProduct(DeleteProductRequest request) {
        Product product = this.findProductById(request.getId());

        ProductDTO productDTO = ProductDTO.convertToProductDTO(product);

        productRepository.deleteById(request.getId());

        return ProductDTO.Response.builder().product(productDTO).build();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<ProductDTO.Response> deleteProducts(List<DeleteProductRequest> requests) {
        List<ProductDTO.Response> responses = new ArrayList<>();

        for (DeleteProductRequest request : requests) {
            responses.add(deleteProduct(request));
        }

        return responses;
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RestApiException(ProductErrorCode.NOT_REGISTRED));
    }

}
