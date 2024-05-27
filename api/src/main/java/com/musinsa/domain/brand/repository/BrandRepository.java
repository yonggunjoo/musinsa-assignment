package com.musinsa.domain.brand.repository;

import com.musinsa.common.entity.Category;
import com.musinsa.domain.brand.dto.BrandLowestPriceDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Category, Long> {
    @Query(value = "WITH BrandTotalPrice AS (\n" +
            "    SELECT\n" +
            "        b.ID AS brandId,\n" +
            "        b.NAME AS brandName,\n" +
            "        SUM(p.PRICE) AS totalPrice\n" +
            "    FROM\n" +
            "        PRODUCT p\n" +
            "    JOIN\n" +
            "        BRAND b ON p.BRAND_ID = b.ID\n" +
            "    GROUP BY\n" +
            "        b.ID, b.NAME\n" +
            "),\n" +
            "MinBrandTotalPrice AS (\n" +
            "    SELECT\n" +
            "        btp.brandId,\n" +
            "        btp.brandName,\n" +
            "        btp.totalPrice\n" +
            "    FROM\n" +
            "        BrandTotalPrice btp\n" +
            "    WHERE\n" +
            "        btp.totalPrice = (SELECT MIN(totalPrice) FROM BrandTotalPrice)\n" +
            ")\n" +
            "SELECT\n" +
            "    mbtp.brandName AS brandName,\n" +
            "    c.NAME AS categoryName,\n" +
            "    p.PRICE AS categoryPrice,\n" +
            "    mbtp.totalPrice AS totalPrice\n" +
            "FROM\n" +
            "    MinBrandTotalPrice mbtp\n" +
            "JOIN\n" +
            "    PRODUCT p ON mbtp.brandId = p.BRAND_ID\n" +
            "JOIN\n" +
            "    CATEGORY c ON p.CATEGORY_ID = c.ID;\n"
            , nativeQuery = true)
    List<BrandLowestPriceDTO.Projection> getSingleBrandCategoryLowestPrice();
}
