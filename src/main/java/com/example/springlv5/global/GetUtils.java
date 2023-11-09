package com.example.springlv5.global;

import com.example.springlv5.domain.user.UserRepository;
import com.example.springlv5.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUtils {
    private final UserRepository userRepository;
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() ->
            new NullPointerException("해당 유저는 존재하지 않습니다.")
        );
    }
}
