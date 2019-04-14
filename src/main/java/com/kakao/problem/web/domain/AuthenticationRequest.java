package com.kakao.problem.web.domain;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationRequest implements Serializable {

    private String username;

    private String password;

}