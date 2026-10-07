package com.ejemplo.practica1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplo.practica1.model.Product;
import com.ejemplo.practica1.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.saveProduct(product);

        return "redirect:/admin";
    }
    
    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "formulario-producto";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService
                .getProductById(id)
                .orElseThrow();

        model.addAttribute("product", product);

        return "formulario-producto";
    }
    
    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name", defaultValue = "") String name, Model model) {
        model.addAttribute("products", productService.searchProducts(name));
        model.addAttribute("searchName", name);
        return "products";
    }

    @PostMapping("/edit")
    public String updateProduct(Product product) {
        productService.saveProduct(product);

        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);

        return "redirect:/admin";
    }
}
