package com.example.springlv5.domain.user.dto;

import com.example.springlv5.domain.user.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class SignupRequest extends LoginRequest {
    @NotNull(message = "성별을 선택하세요")
    private Gender gender;

    @NotBlank(message = "전화번호를 입력하세요")
    @Pattern(regexp = "^\\+?(1\\s)?(\\d){0,3}(\\d){8,14}$", message="올바른 전화번호가 아닙니다.")
    private String phoneNumber;

    @NotBlank(message = "주소를 입력하세요")
    @Length(max = 255, message ="입력할 수 있는 글자수를 초과했습니다")
    private String address;
}
