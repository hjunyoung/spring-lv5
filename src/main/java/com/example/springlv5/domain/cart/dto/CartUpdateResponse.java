package com.example.springlv5.domain.cart.dto;

import com.example.springlv5.domain.cart.entity.Cart;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUpdateResponse {
    private Long id;
    private int quantity;

    public static CartUpdateResponse from(Cart cart) {
        return new CartUpdateResponse(cart.getId(), cart.getQuantity());
    }
}
