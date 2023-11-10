package com.example.springlv5.domain.cart;

import com.example.springlv5.domain.cart.entity.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    boolean existsByUserId(Long id) ;

    boolean existsByProductId(Long id);

    Optional<Cart> findByProductIdAndUserId(Long id, Long id1);
}
