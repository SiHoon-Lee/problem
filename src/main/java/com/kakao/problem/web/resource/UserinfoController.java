package com.kakao.problem.web.resource;

import com.kakao.problem.web.domain.AuthenticationRequest;
import com.kakao.problem.web.domain.AuthenticationResponse;
import com.kakao.problem.web.domain.User;
import com.kakao.problem.web.jwt.JwtTokenProvider;
import com.kakao.problem.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UserinfoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository users;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> join(@RequestBody AuthenticationRequest data){

        String userName = data.getUsername();
        String password = data.getPassword();

        this.users.save(new User().builder().username(userName)
                .password(this.passwordEncoder.encode(password))
                .roles(Arrays.asList( "ROLE_USER"))
                .build());

        String token = jwtTokenProvider.createToken(userName, this.users.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username " + userName + "not found")).getRoles());
        return ok(new AuthenticationResponse(userName, token));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody AuthenticationRequest data) {

        String userName = data.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, data.getPassword()));

        String token = jwtTokenProvider.createToken(userName, this.users.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username " + userName + "not found")).getRoles());
        return ok(new AuthenticationResponse(userName, token));
    }
}