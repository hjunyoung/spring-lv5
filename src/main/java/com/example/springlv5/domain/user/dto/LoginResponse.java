package com.example.springlv5.domain.user.dto;

import com.example.springlv5.domain.user.entity.User;
import com.example.springlv5.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LoginResponse {
    private final Long id;
    private final String email;
    private final String gender;
    private final String phoneNumber;
    private final String address;
    private final UserRole role;


    private LoginResponse(Long id, String email, String gender, String phoneNumber, String address,
        UserRole role) {
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public static LoginResponse of(User user) {
        return new LoginResponse(
            user.getId(),
            user.getEmail(),
            user.getGender(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getRole()
        );
    }
}
