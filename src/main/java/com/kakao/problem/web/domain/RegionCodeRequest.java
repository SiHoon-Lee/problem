package com.kakao.problem.web.domain;

import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionCodeRequest {

    @NotNull
    private String regionCode;

}
