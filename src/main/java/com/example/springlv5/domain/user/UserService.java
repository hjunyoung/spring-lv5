package com.example.springlv5.domain.user;

import com.example.springlv5.domain.user.dto.SignupRequest;
import com.example.springlv5.domain.user.entity.User;
import com.example.springlv5.global.ApiResponse;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SuccessBody<Void> signup(SignupRequest signupRequest) {
        User user = User.getEncryptedUserFrom(signupRequest, passwordEncoder, userRepository);
        userRepository.save(user);

        return SuccessBody
            .<Void>builder()
            .message("회원가입 성공")
            .build();
    }
}
