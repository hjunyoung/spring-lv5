package com.example.springlv5.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class PasswordRequest {

    @NotBlank(message = "비밀번호를 입력하세요")
    @Length(min = 8, max = 15, message = "비밀번호는 8자 이상 15자로 입력하세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}",
        message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 포함해야합니다.")
    private String password;
}
