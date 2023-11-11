package com.example.springlv5.domain.product;

import com.example.springlv5.domain.product.dto.ProductRequest;
import com.example.springlv5.domain.product.dto.ProductResponse;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<SuccessBody<ProductResponse>> addProduct(@
        RequestBody @Validated ProductRequest productRequest) {
        SuccessBody<ProductResponse> response = productService.addProduct(productRequest);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<SuccessBody<ProductResponse>> getOneProduct(@PathVariable Long productId) {
        SuccessBody<ProductResponse> response = productService.getOneProduct(productId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @GetMapping("")
    public ResponseEntity<SuccessBody<Page<ProductResponse>>> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "name") SortBy sortby,
            @RequestParam(defaultValue = "desc") String order) {
        System.out.println("page: "+ page + ", sortby: " + sortby + ", order: " + order);
        SuccessBody<Page<ProductResponse>> response = productService.getProducts(
            page - 1,
            sortby.getValue(),
            order
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }
}
