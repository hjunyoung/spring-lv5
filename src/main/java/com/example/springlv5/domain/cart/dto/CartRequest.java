package com.example.springlv5.domain.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class CartRequest {

    @Min(value = 1, message = "수량은 1이상이어야 합니다")
    private int quantity;
}
