package com.kakao.problem.web.resource;

import com.kakao.problem.web.domain.ProgramBulkRequest;
import com.kakao.problem.web.domain.ProgramBulkResponse;
import com.kakao.problem.web.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/program")
public class ProgramResource {

    @Autowired
    private ProgramService programService;

    @PostMapping(value = "/bulk")
    public ResponseEntity<ProgramBulkResponse> saveBulk(ProgramBulkRequest programBulkRequest) throws IOException {

        Long recordCnt = programService.bulk(programBulkRequest);
        return ok(new ProgramBulkResponse(programBulkRequest.getFile().getOriginalFilename(), recordCnt));
    }

}
