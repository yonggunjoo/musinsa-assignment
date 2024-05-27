package com.musinsa.domain.category.service;

import com.musinsa.entity.Category;
import com.musinsa.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImplements implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
