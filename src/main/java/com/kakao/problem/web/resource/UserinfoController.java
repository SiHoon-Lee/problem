package com.kakao.problem.web.resource;

import com.kakao.problem.web.domain.AuthenticationRequest;
import com.kakao.problem.web.domain.AuthenticationResponse;
import com.kakao.problem.web.domain.User;
import com.kakao.problem.web.jwt.JwtTokenProvider;
import com.kakao.problem.web.repository.UserRepository;
import com.kakao.problem.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UserinfoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> join(@RequestBody AuthenticationRequest user){

        String userName = user.getUsername();

        userService.saveUser(user);

        String token = jwtTokenProvider.createToken(userName, this.userService.findByUserName(userName).getRoles());
        return ok(new AuthenticationResponse(userName, token));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody AuthenticationRequest user) {

        String userName = user.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, user.getPassword()));

        String token = jwtTokenProvider.createToken(userName, this.userService.findByUserName(userName).getRoles());
        return ok(new AuthenticationResponse(userName, token));
    }
}