package com.example.springlv5.domain.product;

import com.example.springlv5.aws.S3Service;
import com.example.springlv5.domain.product.dto.ProductRequest;
import com.example.springlv5.domain.product.dto.ProductResponse;
import com.example.springlv5.domain.product.entity.Product;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final S3Service s3Service;
    private final ProductRepository productRepository;
    private final int PRODUCTS_PER_PAGE = 10;

    @Transactional
    public SuccessBody<ProductResponse> addProduct(ProductRequest productRequest,
        MultipartFile multipartFile) {
        Product product = Product.from(productRequest);
        String imageUrl = s3Service.uploadFile(multipartFile);
        product.updateImageUrl(imageUrl);
        productRepository.save(product);

        return SuccessBody
            .<ProductResponse>builder()
            .message("상품등록 성공")
            .data(ProductResponse.from(product))
            .build();
    }

    public SuccessBody<ProductResponse> getOneProduct(Long productId) {
        Product product = Product.getOneProductFrom(productId, productRepository);

        return SuccessBody
            .<ProductResponse>builder()
            .message("상품조회 성공")
            .data(ProductResponse.from(product))
            .build();
    }

    public SuccessBody<Page<ProductResponse>> getProducts(int page, String sortby, String order) {
        // 페이징 처리
        Direction direction = Direction.valueOf(order.toUpperCase());
        Sort sort = Sort.by(direction, sortby);
        Pageable pageable = PageRequest.of(page, PRODUCTS_PER_PAGE, sort);

        Page<Product> products = productRepository.findAll(pageable);

        return SuccessBody
            .<Page<ProductResponse>>builder()
            .message("상품 목록 조회 성공")
            .data(products.map(ProductResponse::from))
            .build();
    }
}
