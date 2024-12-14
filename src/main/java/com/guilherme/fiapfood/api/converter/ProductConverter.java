package com.guilherme.fiapfood.api.converter;

import com.guilherme.fiapfood.api.request.CategoryRequestDTO;
import com.guilherme.fiapfood.api.request.ProductRequestDTO;
import com.guilherme.fiapfood.infrastructure.entity.CategoryEntity;
import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductConverter {


    public ProductEntity toProductEntity(ProductRequestDTO productDTO) {
        return ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .information(productDTO.getInformation())
                .build();
    }


    public CategoryEntity toCategoryEntity(CategoryRequestDTO categoryDTO, String productId) {
        return CategoryEntity.builder()
                .productId(productId)
                .name(categoryDTO.getName())
                .build();
    }

}
