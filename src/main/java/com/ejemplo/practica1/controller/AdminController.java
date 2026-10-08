package com.ejemplo.practica1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejemplo.practica1.service.DatabaseExportService;
import com.ejemplo.practica1.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DatabaseExportService databaseExportService;
    private final ProductService productService;

    @Autowired
    public AdminController(DatabaseExportService databaseExportService, ProductService productService) {
        this.databaseExportService = databaseExportService;
        this.productService = productService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin";
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportDatabase() {

        byte[] sqlFile = databaseExportService.exportDatabaseToSql();

        return ResponseEntity.ok()
                .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=productos.sql"
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(sqlFile);
    }
}
