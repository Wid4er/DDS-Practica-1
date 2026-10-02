package com.ejemplo.practica1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ejemplo.practica1.model.Product;
import com.ejemplo.practica1.repository.ProductRepo;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
    
    public List<Product> searchProducts(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }
}