package com.guilherme.fiapfood.business;

import com.guilherme.fiapfood.infrastructure.entity.CategoryEntity;
import com.guilherme.fiapfood.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity findByProductId(String productId) {
        return categoryRepository.findByProductId(productId);
    }

    public void deleteByProductId(String productId) {
        categoryRepository.deleteByProductId(productId);
    }
}