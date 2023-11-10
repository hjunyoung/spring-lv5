package com.example.springlv5.security.jwt;

import com.example.springlv5.domain.user.UserRepository;
import com.example.springlv5.domain.user.dto.LoginRequest;
import com.example.springlv5.domain.user.dto.LoginResponse;
import com.example.springlv5.domain.user.entity.User;
import com.example.springlv5.domain.user.entity.UserRole;
import com.example.springlv5.exception.ErrorCode;
import com.example.springlv5.global.ApiResponse;
import com.example.springlv5.global.ApiResponse.FailBody;
import com.example.springlv5.global.ApiResponse.SuccessBody;
import com.example.springlv5.global.ResponseStatus;
import com.example.springlv5.security.userdetails.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {

        log.info("로그인 시도");

        try {
            LoginRequest loginRequest = new ObjectMapper()
                .readValue(request.getInputStream(), LoginRequest.class);

            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword(),
                    null
                )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        String email = ((UserDetailsImpl) authResult.getPrincipal()).getEmail();
        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        String token = jwtUtil.createToken(email, role);
        // TODO: refresh token 해보기

        setResponseConfig(response, HttpStatus.OK);

        User user = userRepository.findByEmail(email).get();
        // LoginResponse loginResponse = new LoginResponse(user);
        LoginResponse loginResponse = LoginResponse.of(user);
        SuccessBody<Object> successBody = ApiResponse.SuccessBody
            .builder()
            .status(ResponseStatus.SUCCESS.getStatus())
            .message("로그인 성공. (Custom success message")
            .data(loginResponse)
            .build();

        String apiResponseJson = objectMapper.writeValueAsString(successBody);
        response.getWriter().write(apiResponseJson);

        jwtUtil.addJwtToCookie(token, response);
    }

    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");

        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        HttpStatus httpStatus = errorCode.getStatus();
        setResponseConfig(response, httpStatus);

        FailBody failBody = ApiResponse.FailBody
            .builder()
            .status(ResponseStatus.FAIL.getStatus())
            .message("로그인에 실패했습니다. (Custom error message)")
            .build();

        String apiResponseJson = objectMapper.writeValueAsString(failBody);
        response.getWriter().write(apiResponseJson);
    }

    private void setResponseConfig(HttpServletResponse response, HttpStatus httpStatus) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(httpStatus.value());
    }
}