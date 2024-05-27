package com.musinsa.domain.category.repository;

import com.musinsa.entity.Category;
import com.musinsa.domain.category.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.category.dto.CategoryPriceSearchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

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
