package com.kakao.problem.web.resource;

import com.kakao.problem.web.domain.AuthenticationResponse;
import com.kakao.problem.web.domain.User;
import com.kakao.problem.web.jwt.JwtTokenProvider;
import com.kakao.problem.web.jwt.exception.InvalidJwtAuthenticationException;
import com.kakao.problem.web.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository users;

    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest req) {

        String reqToken = jwtTokenProvider.resolveToken(req);
        Authentication authentication = jwtTokenProvider.getAuthentication(reqToken);
        User user = (User)authentication.getPrincipal();

        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        return ok(new AuthenticationResponse(user.getUsername(), token));
    }
}