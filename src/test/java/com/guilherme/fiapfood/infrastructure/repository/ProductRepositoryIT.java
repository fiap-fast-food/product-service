package com.guilherme.fiapfood.infrastructure.repository;

import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static com.guilherme.fiapfood.utils.ProductHelper.gerarProductEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProductRepositoryIT {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void mustAllowRegisterProduct() {

        // Arrange
        var id = UUID.randomUUID().toString();
        var product = gerarProductEntity();

        product.setId(id);

        // Act
        var productReceived = productRepository.save(product);

        // Assert
        assertThat(productReceived)
                .isInstanceOf(ProductEntity.class)
                .isNotNull();

        assertThat(productReceived.getId()).isEqualTo(id);
        assertThat(productReceived.getName()).isEqualTo(product.getName());
        assertThat(productReceived.getInformation()).isEqualTo(product.getInformation());
        assertThat(productReceived.getPrice()).isEqualTo(product.getPrice());

    }

    @Test
    void mustAllowFindProduct() {
        // Arrange
        var id = UUID.randomUUID().toString();
        saveProductWithUmId(id);

        // Act
        Optional<ProductEntity> productReceivedOptional = productRepository.findById(id);

        // Assert
        assertThat(productReceivedOptional).isPresent();

        productReceivedOptional.ifPresent(productReceived -> {
            assertThat(productReceived.getId()).isEqualTo(id);
        });
    }

    @Test
    void mustAllowDeleteProduct() {
        var id = UUID.randomUUID().toString();
        saveProductWithUmId(id);

        // Act
        productRepository.deleteById(id);
        Optional<ProductEntity> productReceivedOptional = productRepository.findById(id);

        assertThat(productReceivedOptional).isEmpty();
    }

    @Test
    void mustAllowFindAllProducts() {

        // Arrange
        for (int i = 1; i <= 3; i++) {
            var id = UUID.randomUUID().toString();
            saveProductWithUmId(id);
        }

        // act
        var productsReceived = productRepository.findAll();

        //Assert
        assertThat(productsReceived).hasSizeGreaterThan(0);

    }


    private ProductEntity saveProductWithUmId(String id) {
        ProductEntity product = gerarProductEntity();
        product.setId(id);
        return productRepository.save(product);
    }

}