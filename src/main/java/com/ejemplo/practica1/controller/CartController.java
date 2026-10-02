package com.ejemplo.practica1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplo.practica1.model.Product;
import com.ejemplo.practica1.service.CartService;
import com.ejemplo.practica1.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(
            CartService cartService,
            ProductService productService) {

        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String viewCart(Model model) {

        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getTotal());

        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int quantity) {

        Product product = productService
                .getProductById(id)
                .orElseThrow();

        if (quantity > 0) {
            cartService.addProduct(product, quantity);
        }

        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String updateQuantity(
            @PathVariable Long id,
            @RequestParam int quantity) {

        cartService.updateQuantity(id, quantity);

        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {

        cartService.removeProduct(id);

        return "redirect:/cart";
    }
}