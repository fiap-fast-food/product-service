package com.guilherme.fiapfood.business;

import com.guilherme.fiapfood.api.converter.ProductConverter;
import com.guilherme.fiapfood.api.converter.ProductMapper;
import com.guilherme.fiapfood.api.request.ProductRequestDTO;
import com.guilherme.fiapfood.api.response.ProductResponseDTO;
import com.guilherme.fiapfood.infrastructure.entity.CategoryEntity;
import com.guilherme.fiapfood.infrastructure.entity.ProductEntity;
import com.guilherme.fiapfood.infrastructure.exceptions.BusinessException;
import com.guilherme.fiapfood.infrastructure.exceptions.ProductNotFoundException;
import com.guilherme.fiapfood.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;


    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public ProductResponseDTO engraveProducts(ProductRequestDTO productRequestDTO) {

        try {
            notNull(productRequestDTO, "Product data is mandatory");
            var productEntity = saveProduct(productConverter.toProductEntity(productRequestDTO));
            var categoryEntity = categoryService.saveCategory(
                    productConverter.toCategoryEntity(productRequestDTO.getCategory(), productEntity.getId()));
            return productMapper.toProductResponseDTO(productEntity, categoryEntity);
        } catch (Exception e) {
            throw new BusinessException("Error writing product data", e);
        }
    }

    public List<ProductResponseDTO> findAll() {
        List<ProductEntity> products = productRepository.findAll();

        return products.stream()
                .map(product -> {
                    CategoryEntity category = categoryService.findByProductId(product.getId());
                    return productMapper.toProductResponseDTO(product, category);
                })
                .toList();
    }

    public ProductResponseDTO findById(String id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        var category = categoryService.findByProductId(product.getId());
        return productMapper.toProductResponseDTO(product, category);
    }

    public ProductResponseDTO updateById(String id, ProductRequestDTO productRequestDTO) {
        findById(id);
        var productEntity = productConverter.toProductEntity(productRequestDTO);
        var category = categoryService.findByProductId(id);
        productEntity.setId(id);

        return productMapper.toProductResponseDTO(productRepository.save(productEntity), category);
    }

    public void deleteById(String id) {
        findById(id);
        productRepository.deleteById(id);
        categoryService.deleteByProductId(id);
    }
}
