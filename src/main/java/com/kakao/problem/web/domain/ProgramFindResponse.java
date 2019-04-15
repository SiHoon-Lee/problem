package com.kakao.problem.web.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramFindResponse {

    private String region;

    private List<ProgramSimpleInfo> programs;

    public ProgramFindResponse(String region, List<Program> programs) {
        this.region = region;
        this.programs = programs.stream().map(program -> new ProgramSimpleInfo(program.getProgramName(), program.getCategoryName())).collect(Collectors.toList());
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class ProgramSimpleInfo{

        private String prgmName;

        private String theme;

        public ProgramSimpleInfo(String prgmName, String theme) {
            this.prgmName = prgmName;
            this.theme = theme;
        }
    }
}
