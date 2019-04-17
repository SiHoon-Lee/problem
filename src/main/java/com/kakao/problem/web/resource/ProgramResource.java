package com.kakao.problem.web.resource;

import com.kakao.problem.web.domain.*;
import com.kakao.problem.web.service.ProgramService;
import com.kakao.problem.web.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/program")
public class ProgramResource {

    @Autowired
    private ProgramService programService;

    @Autowired
    private RegionService regionService;

    @PostMapping("/bulk")
    public ResponseEntity<ProgramBulkResponse> saveBulk(ProgramBulkRequest programBulkRequest) throws IOException {

        Long recordCnt = programService.bulk(programBulkRequest);
        return ok(new ProgramBulkResponse(programBulkRequest.getFile().getOriginalFilename(), recordCnt));
    }

    @PostMapping("/service")
    public ResponseEntity<ProgramFindResponse> getProgram(@RequestBody @Valid ProgramFindRequest programFindRequest){

        Region region = regionService.getRegion(programFindRequest.getServiceArea());
        if(region == null) new Exception("Not Found ServiceArea");

        List<Program> programList = programService.getProgramsByRegionCode(region);
        return ok(new ProgramFindResponse(region.getRegionId(), programList));
    }

}
