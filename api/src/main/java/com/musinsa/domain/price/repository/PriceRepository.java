package com.musinsa.domain.price.repository;

import com.musinsa.domain.price.dto.BrandLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryPriceSearchDTO;
import com.musinsa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Product, Long> {

    @Query(value = "WITH CategoryLowestPrice AS (\n" +
            "    SELECT\n" +
            "        c.ID AS CATEGORY_ID,\n" +
            "        c.NAME AS CATEGORY_NAME,\n" +
            "        MIN(p.PRICE) AS LOWEST_PRICE\n" +
            "    FROM\n" +
            "        CATEGORY c\n" +
            "    JOIN\n" +
            "        PRODUCT p ON p.CATEGORY_ID = c.ID\n" +
            "    GROUP BY\n" +
            "        c.ID,\n" +
            "        c.NAME\n" +
            ")\n" +
            "SELECT\n" +
            "    clp.CATEGORY_NAME AS categoryName,\n" +
            "    clp.LOWEST_PRICE AS lowestPrice,\n" +
            "    MAX(b.NAME) AS brandName,\n" +
            "    (SELECT SUM(LOWEST_PRICE) FROM CategoryLowestPrice) AS totalLowestPrice\n" +
            "FROM\n" +
            "    CategoryLowestPrice clp\n" +
            "JOIN\n" +
            "    PRODUCT p ON p.CATEGORY_ID = clp.CATEGORY_ID AND p.PRICE = clp.LOWEST_PRICE\n" +
            "JOIN\n" +
            "    BRAND b ON p.BRAND_ID = b.ID\n" +
            "GROUP BY\n" +
            "    clp.CATEGORY_NAME,\n" +
            "    clp.LOWEST_PRICE\n" +
            "ORDER BY\n" +
            "    clp.CATEGORY_ID;", nativeQuery = true)
    List<CategoryLowestPriceDTO.Projection> getCategoryLowestPriceBrand();

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

    @Query(value = "WITH CategoryLowestPrice AS (\n" +
            "    SELECT\n" +
            "        c.ID AS CATEGORY_ID,\n" +
            "        c.NAME AS CATEGORY_NAME,\n" +
            "        MIN(p.PRICE) AS LOWEST_PRICE\n" +
            "    FROM\n" +
            "        CATEGORY c\n" +
            "    JOIN\n" +
            "        PRODUCT p ON p.CATEGORY_ID = c.ID\n" +
            "    GROUP BY\n" +
            "        c.ID,\n" +
            "        c.NAME\n" +
            ")\n" +
            "SELECT\n" +
            "    clp.CATEGORY_NAME AS categoryName,\n" +
            "    clp.LOWEST_PRICE AS lowestPrice,\n" +
            "    b.NAME AS brandName\n" +
            "FROM\n" +
            "    CategoryLowestPrice clp\n" +
            "JOIN\n" +
            "    PRODUCT p ON p.CATEGORY_ID = clp.CATEGORY_ID AND p.PRICE = clp.LOWEST_PRICE\n" +
            "JOIN\n" +
            "    BRAND b ON p.BRAND_ID = b.ID\n" +
            "WHERE\n" +
            "    clp.CATEGORY_NAME = :categoryName\n" +
            "AND p.price = clp.LOWEST_PRICE; ", nativeQuery = true)
    List<CategoryPriceSearchDTO.Projection> getCategoryLowestPriceInfo(@Param("categoryName") String categoryName);

    @Query(value = "WITH CategoryHighestPrice AS (\n" +
            "    SELECT\n" +
            "        c.ID AS CATEGORY_ID,\n" +
            "        c.NAME AS CATEGORY_NAME,\n" +
            "        MAX(p.PRICE) AS HIGHEST_PRICE\n" +
            "    FROM\n" +
            "        CATEGORY c\n" +
            "    JOIN\n" +
            "        PRODUCT p ON p.CATEGORY_ID = c.ID\n" +
            "    GROUP BY\n" +
            "        c.ID,\n" +
            "        c.NAME\n" +
            ")\n" +
            "SELECT\n" +
            "    chp.CATEGORY_NAME AS categoryName,\n" +
            "    chp.HIGHEST_PRICE AS highestPrice,\n" +
            "    b.NAME AS brandName\n" +
            "FROM\n" +
            "    CategoryHighestPrice chp\n" +
            "JOIN\n" +
            "    PRODUCT p ON p.CATEGORY_ID = chp.CATEGORY_ID AND p.PRICE = chp.HIGHEST_PRICE\n" +
            "JOIN\n" +
            "    BRAND b ON p.BRAND_ID = b.ID\n" +
            "WHERE\n" +
            "    chp.CATEGORY_NAME = :categoryName\n" +
            "AND p.price = chp.HIGHEST_PRICE;", nativeQuery = true)
    List<CategoryPriceSearchDTO.Projection> getCategoryHighestPriceInfo(@Param("categoryName") String categoryName);


//    @Query(value = "\n" +
//            "WITH CategoryLowestPrice AS (\n" +
//            "    SELECT\n" +
//            "        c.ID AS CATEGORY_ID,\n" +
//            "        c.NAME AS CATEGORY_NAME,\n" +
//            "        MIN(p.PRICE) AS LOWEST_PRICE\n" +
//            "    FROM\n" +
//            "        CATEGORY c\n" +
//            "    JOIN\n" +
//            "        PRODUCT p ON p.CATEGORY_ID = c.ID\n" +
//            "    GROUP BY\n" +
//            "        c.ID,\n" +
//            "        c.NAME\n" +
//            ")\n" +
//            "SELECT\n" +
//            "    clp.CATEGORY_NAME AS categoryName,\n" +
//            "    clp.LOWEST_PRICE AS lowestPrice,\n" +
//            "    b.NAME AS brandName\n" +
//            "FROM\n" +
//            "    CategoryLowestPrice clp\n" +
//            "JOIN \n" +
//            "    PRODUCT p ON p.CATEGORY_ID = clp.CATEGORY_ID\n" +
//            "JOIN \n" +
//            "    BRAND b ON p.BRAND_ID = b.ID\n" +
//            "WHERE\n" +
//            "    clp.CATEGORY_NAME = :categoryName \n" +
//            "    AND p.price = clp.LOWEST_PRICE;\n", nativeQuery = true)
//    List<CategoryPriceSearchDTO.Projection> getCategoryLowestPriceInfo(String categoryName);


//    @Query(value = "WITH CategoryHighestPrice AS (\n" +
//            "    SELECT\n" +
//            "        c.ID AS CATEGORY_ID,\n" +
//            "        c.NAME AS CATEGORY_NAME,\n" +
//            "        MAX(p.PRICE) AS HIGHEST_PRICE\n" +
//            "    FROM\n" +
//            "        CATEGORY c\n" +
//            "    JOIN\n" +
//            "        PRODUCT p ON p.CATEGORY_ID = c.ID\n" +
//            "    GROUP BY\n" +
//            "        c.ID,\n" +
//            "        c.NAME\n" +
//            ")\n" +
//            "SELECT\n" +
//            "    chp.CATEGORY_NAME AS categoryName,\n" +
//            "    chp.HIGHEST_PRICE AS highestPrice,\n" +
//            "    b.NAME AS brandName\n" +
//            "FROM\n" +
//            "    CategoryHighestPrice chp\n" +
//            "JOIN \n" +
//            "    PRODUCT p ON p.CATEGORY_ID = chp.CATEGORY_ID\n" +
//            "JOIN \n" +
//            "    BRAND b ON p.BRAND_ID = b.ID\n" +
//            "WHERE\n" +
//            "    chp.CATEGORY_NAME = :categoryName \n" +
//            "    AND p.price = chp.HIGHEST_PRICE;\n", nativeQuery = true)
//    List<CategoryPriceSearchDTO.Projection> getCategoryHighestPriceInfo(@Param(value = "categoryName") String categoryName);
}
