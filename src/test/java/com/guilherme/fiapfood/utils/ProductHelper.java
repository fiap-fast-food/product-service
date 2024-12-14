package com.guilherme.fiapfood.utils;

import com.guilherme.fiapfood.api.request.CategoryRequestDTO;
import com.guilherme.fiapfood.api.request.ProductRequestDTO;
import com.guilherme.fiapfood.api.response.ProductResponseDTO;
import com.guilherme.fiapfood.infrastructure.entity.CategoryEntity;
import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;

import java.util.UUID;

public class ProductHelper {

    public static ProductEntity gerarProductEntity() {
        return ProductEntity.builder()
                .name("Hamburguer")
                .price(12.0)
                .information("Information")
                .build();
    }


    public static ProductEntity gerarProductEntityComId() {
        return ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("Hamburguer")
                .price(12.0)
                .information("Information")
                .build();
    }

    public static ProductRequestDTO gerarProductRequest() {
        return ProductRequestDTO.builder()
                .name("Hamburguer")
                .price(12.0)
                .information("Information")
                .category(gerarCategoryRequestDTO())
                .build();
    }

    public static CategoryEntity gerarCategoryEntity() {
        return CategoryEntity
                .builder()
                .name("Hamburguers")
                .build();
    }

    public static CategoryRequestDTO gerarCategoryRequestDTO() {
        return CategoryRequestDTO.builder()
                .name("Hamburguers")
                .build();
    }

    public static ProductResponseDTO gerarProductResponse() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                UUID.randomUUID().toString(),
                "Hamburguer",
                12.0,
                "Information",
                gerarCategoryRequestDTO()
                );
        return productResponseDTO;
    }
}
