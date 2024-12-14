package com.guilherme.fiapfood.api.converter;

import com.guilherme.fiapfood.api.response.ProductResponseDTO;
import com.guilherme.fiapfood.infrastructure.entity.CategoryEntity;
import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "information", source = "product.information")
    @Mapping(target = "category", source = "categoryEntity")
    ProductResponseDTO toProductResponseDTO(ProductEntity product, CategoryEntity categoryEntity);
}
