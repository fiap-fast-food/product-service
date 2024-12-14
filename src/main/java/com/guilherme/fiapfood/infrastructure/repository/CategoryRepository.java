package com.guilherme.fiapfood.infrastructure.repository;

import com.guilherme.fiapfood.infrastructure.entity.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {
    CategoryEntity findByProductId(String productId);

    void deleteByProductId(String productId);
}
