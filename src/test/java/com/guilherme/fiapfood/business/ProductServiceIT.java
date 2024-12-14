package com.guilherme.fiapfood.business;

import com.guilherme.fiapfood.api.converter.ProductConverter;
import com.guilherme.fiapfood.api.converter.ProductMapper;
import com.guilherme.fiapfood.api.request.ProductRequestDTO;
import com.guilherme.fiapfood.api.response.ProductResponseDTO;
import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;
import com.guilherme.fiapfood.infrastructure.exceptions.BusinessException;
import com.guilherme.fiapfood.infrastructure.exceptions.ProductNotFoundException;
import com.guilherme.fiapfood.infrastructure.repository.ProductRepository;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static com.guilherme.fiapfood.utils.ProductHelper.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceIT {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;



    @Test
    @Severity(SeverityLevel.BLOCKER)
    void mustAllowRegisterProduct() {

        // Arrange
        var product = gerarProductEntity();

        // Act
        var productRegistred = productService.saveProduct(product);

        // Assert
        assertThat(productRegistred).isInstanceOf(ProductEntity.class).isNotNull();

        assertThat(productRegistred.getName()).isEqualTo(product.getName());
        assertThat(productRegistred.getPrice()).isEqualTo(product.getPrice());
        assertThat(productRegistred.getInformation()).isEqualTo(product.getInformation());

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    void mustAllowEngraveProduct() {

        // Arrange
        var productRequestDTO = gerarProductRequest();

        // Act
        var productRegistred = productService.engraveProducts(productRequestDTO);

        // Assert
        assertThat(productRegistred).isInstanceOf(ProductResponseDTO.class).isNotNull();
        assertThat(productRegistred.name()).isEqualTo(productRequestDTO.getName());
        assertThat(productRegistred.price()).isEqualTo(productRequestDTO.getPrice());
        assertThat(productRegistred.information()).isEqualTo(productRequestDTO.getInformation());

    }

    @Test
    void mustGenerateExceptionWhenCategoryNull() {
        ProductRequestDTO productRequestDTO = gerarProductRequest();
        productRequestDTO.setCategory(null);

        assertThatThrownBy(() -> productService.engraveProducts(productRequestDTO))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Error writing product data");
    }

    @Test
    void mustAllowFindProduct() throws ProductNotFoundException {
        // Arrange
        var id = UUID.fromString("a0d94841-8ac4-4346-af6d-dbb82bd316cc").toString();

        // Act
        ProductResponseDTO productReceived = productService.findById(id);

        // Assert
        Assertions.assertThat(gerarProductEntityComId().getName()).isEqualTo(productReceived.name());
        Assertions.assertThat(gerarProductEntityComId().getId()).isNotNull();
    }

    @Test
    void mustGenerateExceptionWhenIdDoesNotExist() {
        var id = UUID.randomUUID().toString();

        assertThatThrownBy(() -> productService.findById(id))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product not found");
    }

    @Test
    void mustAllowUpdateProduct() throws ProductNotFoundException {
        var id = UUID.fromString("a0d94841-8ac4-4346-af6d-dbb82bd316cc").toString();
        var oldProduct = gerarProductEntity();
        oldProduct.setId(id);

        var newProduct = new ProductEntity();
        newProduct.setId(oldProduct.getId());
        newProduct.setName("teste");
        newProduct.setInformation("teste");

        // act
        ProductResponseDTO productReceived = productService.updateById(id, gerarProductRequest());

        // assert
        assertThat(productReceived).isInstanceOf(ProductResponseDTO.class);
        assertThat(productReceived).isNotNull();
    }

    @Test
    void mustGenerateExceptionWhenIdDoesNotExistInUpdate() {
        var id = UUID.randomUUID().toString();

        assertThatThrownBy(() -> productService.updateById(id, gerarProductRequest()))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product not found");
    }

    @Test
    void mustGenerateExceptionProductNotFoundInDelete() {
        var id = UUID.randomUUID().toString();

        assertThatThrownBy(() -> productService.deleteById(id))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product not found");
    }


    @Test
    void mustAllowListProducts() {

        // Act
        var productsReceived = productService.findAll();

        // Assert
        Assertions.assertThat(productsReceived).isNotEmpty();
    }


}