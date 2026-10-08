package com.ejemplo.practica1.service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.practica1.model.Product;
import com.ejemplo.practica1.repository.ProductRepo;

@Service
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProductRepo productRepo;

    @Autowired
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

    @Transactional
    public void deleteProduct(Long id) {
        entityManager.createQuery("delete from CartItem item where item.product.id = :productId")
                .setParameter("productId", id)
                .executeUpdate();
        productRepo.deleteById(id);
    }
    
    public List<Product> searchProducts(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }
}
