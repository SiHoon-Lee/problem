package com.kakao.problem.web.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationResponse {

    private String userName;

    private String token;

    public AuthenticationResponse(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

}
