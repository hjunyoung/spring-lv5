package com.example.springlv5.domain.cart.entity;

import com.example.springlv5.domain.cart.CartRepository;
import com.example.springlv5.domain.product.entity.Product;
import com.example.springlv5.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;


    private Cart(Product product, int quantity, User user) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public static Cart of(Product product, int quantity, User user) {
        return new Cart(product, quantity, user);
    }
}
