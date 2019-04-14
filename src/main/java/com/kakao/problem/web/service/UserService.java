package com.kakao.problem.web.service;

import com.kakao.problem.web.domain.AuthenticationRequest;
import com.kakao.problem.web.domain.User;
import com.kakao.problem.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository users;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    public User findByUserName(String username){
        return this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    public void saveUser(AuthenticationRequest user){
        this.users.save(new User().builder().username(user.getUsername())
                .password(this.passwordEncoder.encode(user.getPassword()))
                .roles(Arrays.asList( "ROLE_USER"))
                .build());
    }

}
