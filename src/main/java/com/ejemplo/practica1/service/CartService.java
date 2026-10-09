package com.ejemplo.practica1.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import com.ejemplo.practica1.model.CartItem;
import com.ejemplo.practica1.model.Product;
import com.ejemplo.practica1.repository.ProductRepo;

@Service
@SessionScope
public class CartService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepo productRepo;
    private final List<CartItem> cart = new ArrayList<>();


    /*
    public CartService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }*/

    @Transactional
    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            return;
        }

        for (CartItem item : getCartItems()) {

            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        String username = getUsername();
        if (username == null) {
            cart.add(new CartItem(product, quantity));
        } else {
            entityManager.persist(new CartItem(username, product, quantity));
        }
    }

    @Transactional
    public void updateQuantity(Long productId, int quantity) {

        if (quantity <= 0) {
            removeProduct(productId);
            return;
        }

        for (CartItem item : getCartItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    @Transactional
    public void removeProduct(Long productId) {
        for (CartItem item : getCartItems()) {
            if (item.getProduct().getId().equals(productId)) {
                if (getUsername() == null) {
                    cart.remove(item);
                } else {
                    entityManager.remove(item);
                }
                return;
            }
        }
    }

    @Transactional(readOnly = true)
    public List<CartItem> getCartItems() {
        String username = getUsername();
        if (username != null) {
            return entityManager.createQuery(
                    "select item from CartItem item join fetch item.product "
                    + "where item.username = :username order by item.id", CartItem.class)
                    .setParameter("username", username)
                    .getResultList();
        }

        cart.removeIf(item -> {
            Product currentProduct = productRepo.findById(item.getProduct().getId()).orElse(null);
            if (currentProduct == null) {
                return true;
            }
            item.setProduct(currentProduct);
            return false;
        });
        return cart;
    }

    @Transactional(readOnly = true)
    public double getTotal() {
        return getCartItems().stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }
}
