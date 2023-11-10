package com.example.springlv5.domain.cart;

import com.example.springlv5.domain.cart.entity.Cart;
import com.example.springlv5.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<List<Cart>> findAllByUserId(Long id);

    Optional<Cart> findByProductIdAndUser(Long productId, User user);
}
