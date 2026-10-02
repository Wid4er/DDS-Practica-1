package com.ejemplo.practica1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.practica1.model.CartItem;
import com.ejemplo.practica1.model.Product;

@Service
public class CartService {

    private final List<CartItem> cart = new ArrayList<>();

    public void addProduct(Product product, int quantity) {

        for (CartItem item : cart) {

            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        cart.add(new CartItem(product, quantity));
    }

    public void updateQuantity(Long productId, int quantity) {

        if (quantity <= 0) {
            removeProduct(productId);
            return;
        }

        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public void removeProduct(Long productId) {
        cart.removeIf(
            item -> item.getProduct().getId().equals(productId)
        );
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public double getTotal() {
        return cart.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }
}