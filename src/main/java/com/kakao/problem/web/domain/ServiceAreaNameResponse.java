package com.kakao.problem.web.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceAreaNameResponse {

    private String region;

    private List<ProgramSimpleInfo> programs;

    public ServiceAreaNameResponse(String region, List<Program> programs) {

        this.region = region;
        if(programs != null) this.programs = programs.stream().map(program -> new ProgramSimpleInfo(program.getProgramName(), program.getCategoryName())).collect(Collectors.toList());
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
