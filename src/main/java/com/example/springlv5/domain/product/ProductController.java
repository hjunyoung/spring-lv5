package com.example.springlv5.domain.product;

import com.example.springlv5.domain.product.dto.ProductRequest;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<SuccessBody<?>> addProduct(@RequestBody @Validated ProductRequest productRequest) {
        SuccessBody<?> response = productService.addProduct(productRequest);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

}
