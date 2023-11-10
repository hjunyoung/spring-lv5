package com.example.springlv5.domain.cart.dto;

import com.example.springlv5.domain.cart.entity.Cart;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartResponse {

    private Long id;
    private String name;
    private double price;
    private int stock;
    private String category;
    private String description;
    private String imageUrl;
    private int quantity;

    public static CartResponse from(Cart cart) {
        return new CartResponse(
            cart.getId(),
            cart.getProduct().getName(),
            cart.getProduct().getPrice(),
            cart.getProduct().getStock(),
            cart.getProduct().getCategory(),
            cart.getProduct().getDescription(),
            cart.getProduct().getImageUrl(),
            cart.getQuantity()
        );

    }

}
