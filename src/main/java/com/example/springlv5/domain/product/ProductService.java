package com.example.springlv5.domain.product;

import com.example.springlv5.domain.product.dto.ProductRequest;
import com.example.springlv5.domain.product.dto.ProductResponse;
import com.example.springlv5.domain.product.entity.Product;
import com.example.springlv5.exception.DuplicatedException;
import com.example.springlv5.exception.ErrorCode;
import com.example.springlv5.exception.NotFoundException;
import com.example.springlv5.global.ApiResponse;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



    public SuccessBody<?> addProduct(ProductRequest productRequest) {
        Product product = Product.from(productRequest);
        productRepository.save(product);
        return SuccessBody
            .builder()
            .message("상품등록 성공")
            .data(ProductResponse.from(product))
            .build();
    }

    public SuccessBody<?> getOneProduct(Long productId) {
        Product product = Product.getOneProductFrom(productId, productRepository);
        return SuccessBody
            .builder()
            .message("상품조회 성공")
            .data(ProductResponse.from(product))
            .build();
    }
}
