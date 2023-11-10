package com.example.springlv5.domain.product.entity;

import com.example.springlv5.domain.cart.entity.Cart;
import com.example.springlv5.domain.product.dto.ProductRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;

    @Column
    @Builder.Default
    private int stock = 0;

    @Column(nullable = false, length = 32)
    private String category;

    @Column
    private String description;
    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartList = new ArrayList<>();

    private Product(String name, double price, int stock, String category, String description,
        String imageUrl) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static Product from(ProductRequest productRequest) {
        return new Product(
            productRequest.getName(),
            productRequest.getPrice(),
            productRequest.getStock(),
            productRequest.getCategory(),
            productRequest.getDescription(),
            productRequest.getImageUrl()
        );
    }
}
