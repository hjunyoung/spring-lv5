package com.example.springlv5.domain.product.dto;

import com.example.springlv5.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponse {

    private Long id;
    private String name;
    private double price;
    private int stock;
    private String category;
    private String description;
    private String imageUrl;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock(),
            product.getCategory(),
            product.getDescription(),
            product.getImageUrl()
        );
    }

}
