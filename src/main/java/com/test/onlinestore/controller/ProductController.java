package com.test.onlinestore.controller;

import com.test.onlinestore.model.Product;
import com.test.onlinestore.service.ProductService;
import com.test.onlinestore.service.ProductService;
import com.test.onlinestore.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        var products = productService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Products fetched successfully", products));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable Long id) {
        var product =  productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Product not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Product found", product));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody Product product) {
        var saved = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Product created", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(@PathVariable Long id, @RequestBody Product updated) {
        var product = productService.updateProduct(id, updated);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Product not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Product updated", product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Product deleted", null));
    }
}

