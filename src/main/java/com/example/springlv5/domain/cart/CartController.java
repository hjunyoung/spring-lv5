package com.example.springlv5.domain.cart;

import com.example.springlv5.domain.cart.dto.CartRequest;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import com.example.springlv5.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{productId}")
    public ResponseEntity<?> addCart(@PathVariable Long productId,
        @RequestBody @Validated CartRequest cartRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        SuccessBody<?> response = cartService.addCart(productId, cartRequest,
            userDetails.getUser());
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("")
    public ResponseEntity<?> getCarts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        SuccessBody<?> response = cartService.getCarts(userDetails.getUser());
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }
}
