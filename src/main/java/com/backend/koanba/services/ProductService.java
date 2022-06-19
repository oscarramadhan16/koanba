package com.backend.koanba.services;

import com.backend.koanba.controllers.request.ProductRequest;
import com.backend.koanba.entities.ProductEntity;
import com.backend.koanba.exceptions.ProductNotFoundException;
import com.backend.koanba.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity create(ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(UUID.randomUUID());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setStock(productRequest.getStock());

        return this.productRepository.save(productEntity);
    }

    public ProductEntity update(ProductRequest productRequest, String id) throws ProductNotFoundException {
        ProductEntity productEntity = this.productRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ProductNotFoundException("product id not found"));
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setStock(productRequest.getStock());
        productEntity.setPrice(productRequest.getPrice());

        return this.productRepository.save(productEntity);
    }

    public ProductEntity get(String id) throws ProductNotFoundException {
        ProductEntity product = this.productRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ProductNotFoundException("product id not found"));
        return product;
    }
}
