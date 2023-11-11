package com.example.springlv5.domain.user;

import com.example.springlv5.domain.user.dto.SignupRequest;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/auth/signup")
    public ResponseEntity<SuccessBody<Void>> signup(@RequestBody @Validated SignupRequest signupRequest) {
        SuccessBody<Void> response = userService.signup(signupRequest);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }
}
