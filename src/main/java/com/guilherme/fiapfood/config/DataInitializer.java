package com.guilherme.fiapfood.config;

import com.guilherme.fiapfood.infrastructure.entity.CategoryEntity;
import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;
import com.guilherme.fiapfood.infrastructure.repository.CategoryRepository;
import com.guilherme.fiapfood.infrastructure.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DataInitializer(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        ProductEntity product1 = ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("HAMBURGUER TESTE")
                .price(50.0)
                .information("TESTE")
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("REFRIGERANTE TESTE")
                .price(5.0)
                .information("TESTE")
                .build();
        ProductEntity product3 = ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("SORVETE TESTE")
                .price(10.0)
                .information("TESTE")
                .build();

        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        CategoryEntity category1 = CategoryEntity.builder()
                .name("lanche")
                .productId(product1.getId())
                .build();
        CategoryEntity category2 = CategoryEntity.builder()
                .name("bebidas")
                .productId(product2.getId())
                .build();
        CategoryEntity category3 = CategoryEntity.builder()
                .name("sobremesas")
                .productId(product3.getId())
                .build();

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));

        System.out.println("Dados inseridos com sucesso!");
    }
}
