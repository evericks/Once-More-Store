package com.oncemore.store.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItemDTO {
    private UUID cartItemId;
    private UUID productId;
    private int quantity;
    private String nameProduct;
    private String description;
    private BigDecimal price;
    private boolean statusProduct;
    private String url;

    public CartItemDTO(UUID cartItemId,UUID productId, int quantity, String nameProduct, String description, BigDecimal price, boolean statusProduct, String url) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.statusProduct = statusProduct;
        this.url = url;
    }
}
