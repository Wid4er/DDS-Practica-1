package com.ejemplo.practica1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplo.practica1.model.Product;
import com.ejemplo.practica1.model.CartItem;
import com.ejemplo.practica1.service.CartService;
import com.ejemplo.practica1.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
    private CartService cartService;
	@Autowired
    private ProductService productService;

   /* public CartController(
            CartService cartService,
            ProductService productService) {

        this.cartService = cartService;
        this.productService = productService;
    } */

    @GetMapping
    public String viewCart(Model model) {

        List<CartItem> items = cartService.getCartItems();
        model.addAttribute("cartItems", items);
        model.addAttribute("total", items.stream().mapToDouble(CartItem::getSubtotal).sum());

        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(
            @PathVariable("id") Long id,
            @RequestParam(name = "quantity", defaultValue = "1") int quantity) {

        Product product = productService
                .getProductById(id)
                .orElseThrow();

        if (quantity > 0) {
            cartService.addProduct(product, quantity);
        }

        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateQuantity(
            @PathVariable("id") Long id,
            @RequestParam("quantity") int quantity) {

        cartService.updateQuantity(id, quantity);

        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id) {

        cartService.removeProduct(id);

        return "redirect:/cart";
    }
}
