package com.example.springlv5.domain.cart;

import com.example.springlv5.domain.cart.dto.CartRequest;
import com.example.springlv5.domain.cart.entity.Cart;
import com.example.springlv5.domain.product.ProductRepository;
import com.example.springlv5.domain.product.entity.Product;
import com.example.springlv5.domain.user.entity.User;
import com.example.springlv5.exception.DuplicatedException;
import com.example.springlv5.exception.ErrorCode;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public SuccessBody<?> addCart(Long productId, CartRequest cartRequest, User user) {
        Product product = Product.checkIfProductExistsAndGetOne(productId, productRepository);
        int quantity = cartRequest.getQuantity();

        checkIfCartExists(productId, user);

        Cart cart = Cart.of(product, quantity, user);
        cartRepository.save(cart);

        return SuccessBody
            .builder()
            .message("장바구니 추가 성공")
            .build();
    }

    private void checkIfCartExists(Long productId, User user) {
        cartRepository.findByProductIdAndUserId(productId, user.getId())
            .ifPresent(
                (i) -> {
                    throw new DuplicatedException("이미 장바구니에 담겨있는 상품입니다", ErrorCode.RESOURCE_CONFLICT);
                }
            );
    }
}
