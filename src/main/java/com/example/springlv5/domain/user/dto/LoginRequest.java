package com.example.springlv5.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest extends PasswordRequest {

    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "올바른 이메일이 아닙니다.")
    private String email;

}
