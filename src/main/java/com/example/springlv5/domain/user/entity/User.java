package com.example.springlv5.domain.user.entity;

import com.example.springlv5.domain.user.UserRepository;
import com.example.springlv5.domain.user.dto.SignupRequest;
import com.example.springlv5.exception.DuplicatedException;
import com.example.springlv5.exception.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    // user에서 cart 조회하지 않으므로 없어도 됨
    // @OneToMany(mappedBy = "user")
    // private List<Cart> cartList = new ArrayList<>();


    private User(String email, String password, String gender, String phoneNumber, String address,
        UserRole userRole) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = userRole;
    }

    public static User getEncryptedUserFrom(SignupRequest signupRequest,
        PasswordEncoder passwordEncoder,
        UserRepository userRepository) {

        // TODO: remove email check login inside getEncryotedUserFrom method
        // email 중복 확인
        String email = signupRequest.getEmail();
        checkIfEmailExists(email, userRepository);

        // password 암호화
        String encryptedPassword = passwordEncoder.encode(signupRequest.getPassword());

        return new User(
            email,
            encryptedPassword,
            signupRequest.getGender().getValue(),
            signupRequest.getPhoneNumber(),
            signupRequest.getAddress(),
            UserRole.USER
        );
    }

    // TODO: move this method to UserService
    private static void checkIfEmailExists(String email, UserRepository userRepository) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedException("이미 등록된 이메일입니다.", ErrorCode.RESOURCE_CONFLICT);
        }
    }
}
