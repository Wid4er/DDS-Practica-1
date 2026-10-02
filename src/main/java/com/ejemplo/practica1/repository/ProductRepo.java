package com.ejemplo.practica1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.practica1.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);
}