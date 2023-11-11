package com.example.springlv5.domain.cart;

import com.example.springlv5.domain.cart.dto.CartRequest;
import com.example.springlv5.domain.cart.dto.CartResponse;
import com.example.springlv5.domain.cart.dto.CartUpdateResponse;
import com.example.springlv5.domain.cart.entity.Cart;
import com.example.springlv5.domain.product.ProductRepository;
import com.example.springlv5.domain.product.entity.Product;
import com.example.springlv5.domain.user.entity.User;
import com.example.springlv5.exception.DuplicatedException;
import com.example.springlv5.exception.ErrorCode;
import com.example.springlv5.exception.NotFoundException;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import jakarta.validation.constraints.Null;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public SuccessBody<CartResponse> addCart(Long productId, CartRequest cartRequest, User user) {
        Product product = Product.checkIfProductExistsAndGetOne(productId, productRepository);
        checkIfCartExists(productId, user);

        Cart cart = Cart.of(product, cartRequest.getQuantity(), user);
        cartRepository.save(cart);

        return SuccessBody
            .<CartResponse>builder()
            .message("장바구니 추가 성공")
            .build();
    }

    public SuccessBody<List<CartResponse>> getCarts(User user) {
        List<CartResponse> cartResponseList =
            Cart.getCartsOf(user, cartRepository)
                .stream()
                .map(CartResponse::from)
                .toList();

        return SuccessBody
            .<List<CartResponse>>builder()
            .data(cartResponseList)
            .build();
    }


    @Transactional
    public SuccessBody<CartUpdateResponse> updateCart(Long cartId, CartRequest cartRequest, User user) {
        Cart cart = checkIfCartExists(cartId);
        validateOwnership(cart, user);
        cart.updateQuantity(cartRequest.getQuantity());

        return SuccessBody
            .<CartUpdateResponse>builder()
            .message("장바구니 수정 성공")
            .data(CartUpdateResponse.from(cart))
            .build();
    }



    public SuccessBody<Void> deleteCart(Long cartId, User user) {
        Cart cart = checkIfCartExists(cartId);
        validateOwnership(cart, user);
        cartRepository.delete(cart);

        return SuccessBody
            .<Void>builder()
            .message("장바구니 삭제 성공")
            .build();
    }

    private static void validateOwnership(Cart cart, User user) {
        if (!cart.getUser().getEmail().equals(user.getEmail())) {
            throw new SecurityException("접근 권한이 없습니다.");
        }
    }

    private Cart checkIfCartExists(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() ->
            new NotFoundException("장바구니에 담긴 상품이 아닙니다", ErrorCode.RESOURCE_NOT_FOUND));
    }

    // 이 메스드는 Entity에 있으면 좋나?
    // entity로 들어가면 parameter가 3개가 됨. 뭐가 좋나?
    private void checkIfCartExists(Long productId, User user) {
        cartRepository.findByProductIdAndUser(productId, user)
            .ifPresent(
                (i) -> {
                    throw new DuplicatedException("이미 장바구니에 담겨있는 상품입니다",
                        ErrorCode.RESOURCE_CONFLICT);
                }
            );
    }

}
