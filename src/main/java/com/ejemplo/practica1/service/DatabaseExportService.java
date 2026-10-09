package com.ejemplo.practica1.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.practica1.model.Product;
import com.ejemplo.practica1.repository.ProductRepo;

@Service
public class DatabaseExportService {

	@Autowired
    private ProductRepo productRepo;


	/*
    public DatabaseExportService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }*/

    public byte[] exportDatabaseToSql() {

        List<Product> products = productRepo.findAll();

        StringBuilder sql = new StringBuilder();

        for (Product product : products) {

            String name = product.getName().replace("'", "''");

            sql.append("INSERT INTO product (id, name, price) VALUES (")
               .append(product.getId())
               .append(", '")
               .append(name)
               .append("', ")
               .append(product.getPrice())
               .append(");\n");
        }

        return sql.toString().getBytes(StandardCharsets.UTF_8);
    }
}
