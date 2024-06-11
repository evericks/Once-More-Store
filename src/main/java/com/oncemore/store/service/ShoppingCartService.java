package com.oncemore.store.service;

import com.oncemore.store.dto.CartDTO;

import java.util.UUID;

public interface ShoppingCartService {
    String addProductToCart(UUID productId, int quantity);

    CartDTO viewCart();

    void removeProductToCart(UUID productId);

}
