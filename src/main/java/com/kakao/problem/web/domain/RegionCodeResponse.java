package com.kakao.problem.web.domain;

import java.util.List;
import java.util.stream.Collectors;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionCodeResponse {

    private List<Program> programList;

    public RegionCodeResponse(List<Program> programList) {
        this.programList = programList;
    }

}
