package com.test.onlinestore.service;

import com.test.onlinestore.model.Product;
import com.test.onlinestore.model.Product;
import com.test.onlinestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> findAll() { return repository.findAll(); }
    public Product save(Product dt) { return repository.save(dt); }
    public Product findById(Long id) { return repository.findById(id).orElse(null); }
    public Product updateProduct(Long id, Product updatedProduct) {
        return repository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    return repository.save(existingProduct);
                })
                .orElse(null);
    }
    public void deleteById(Long id) { repository.deleteById(id); }
}

