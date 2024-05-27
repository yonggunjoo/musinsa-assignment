package com.musinsa.domain.product.service;


import com.musinsa.domain.product.dto.ProductDTO;
import com.musinsa.domain.product.dto.request.CreateProductRequest;
import com.musinsa.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.domain.product.dto.request.PatchProductRequest;

import java.util.List;

public interface ProductService {
    ProductDTO.Response getProducts();

    ProductDTO.Response createProduct(CreateProductRequest request);

    List<ProductDTO.Response> createProducts(List<CreateProductRequest> request);

    ProductDTO.Response patchProduct(PatchProductRequest request);

    List<ProductDTO.Response> patchProducts(List<PatchProductRequest> request);

    ProductDTO.Response deleteProduct(DeleteProductRequest request);

    List<ProductDTO.Response> deleteProducts(List<DeleteProductRequest> request);


    boolean existsById(Long id);


}
