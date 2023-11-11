package com.example.springlv5.domain.product.entity;

import com.example.springlv5.domain.product.ProductRepository;
import com.example.springlv5.domain.product.dto.ProductRequest;
import com.example.springlv5.exception.ErrorCode;
import com.example.springlv5.exception.NotFoundException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    // Product에서 Cart 조회하지 않으므로 필요없음
    // @OneToMany(mappedBy = "product")
    // private List<Cart> cartList = new ArrayList<>();

    private Product(String name, double price, int stock, String category, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = description;
    }

    public static Product from(ProductRequest productRequest) {
        return new Product(
            productRequest.getName(),
            productRequest.getPrice(),
            productRequest.getStock(),
            productRequest.getCategory(),
            productRequest.getDescription()
        );
    }

    public static Product getOneProductFrom(Long productId, ProductRepository productRepository) {
        return checkIfProductExistsAndGetOne(productId, productRepository);
    }

    // TODO: move this method to ProductService
    public static Product checkIfProductExistsAndGetOne(Long productId, ProductRepository productRepository) {
        return productRepository.findById(productId).orElseThrow(() ->
            new NotFoundException("해당하는 상품은 존재하지 않습니다", ErrorCode.RESOURCE_NOT_FOUND)
        );
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
