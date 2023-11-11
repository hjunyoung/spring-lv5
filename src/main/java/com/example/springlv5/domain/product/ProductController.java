package com.example.springlv5.domain.product;

import com.example.springlv5.domain.product.dto.ProductRequest;
import com.example.springlv5.domain.product.dto.ProductResponse;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
        MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SuccessBody<ProductResponse>> addProduct(
        @RequestPart(value = "imageUrl", required = false) MultipartFile multipartFile,
        @RequestPart ProductRequest productRequest) {

        SuccessBody<ProductResponse> response =
            productService.addProduct(productRequest, multipartFile);

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
