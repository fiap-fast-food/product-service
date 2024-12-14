package com.guilherme.fiapfood.infrastructure.repository;

import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
