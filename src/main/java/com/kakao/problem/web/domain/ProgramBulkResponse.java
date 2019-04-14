package com.kakao.problem.web.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProgramBulkResponse {

    private String fileName;

    private Long recordCnt;

    public ProgramBulkResponse(String fileName, Long recordCnt) {
        this.fileName = fileName;
        this.recordCnt = recordCnt;
    }
}
