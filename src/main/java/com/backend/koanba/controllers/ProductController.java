package com.backend.koanba.controllers;

import com.backend.koanba.controllers.request.ProductRequest;
import com.backend.koanba.entities.ProductEntity;
import com.backend.koanba.exceptions.ProductNotFoundException;
import com.backend.koanba.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //  add
    @PostMapping
    public ResponseEntity<ProductEntity> create(
            @RequestBody ProductRequest productRequest
            ) {
        return ResponseEntity.ok(this.productService.create(productRequest));
    }

    //  update
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(
            @RequestBody ProductRequest productRequest,
            @PathVariable String id
    ) throws ProductNotFoundException {
        ProductEntity productEntity = this.productService.update(productRequest, id);
        return ResponseEntity.ok(productEntity);
    }

    //  get
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> get(@PathVariable String id) throws ProductNotFoundException {
        ProductEntity productEntity = this.productService.get(id);
        return ResponseEntity.ok(productEntity);
    }
}
