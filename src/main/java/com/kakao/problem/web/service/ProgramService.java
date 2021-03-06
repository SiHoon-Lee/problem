package com.kakao.problem.web.service;

import com.kakao.problem.web.domain.Program;
import com.kakao.problem.web.domain.ProgramBulkRequest;
import com.kakao.problem.web.domain.Region;
import com.kakao.problem.web.repository.ProgramRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Service
public class ProgramService {

    @Value("${upload.dir.path}")
    private String uploadPath;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private RegionService regionService;

    @Transactional
    public Long bulk(ProgramBulkRequest programBulkRequest) throws IOException {

        byte[] bytes = programBulkRequest.getFile().getBytes();
        CsvToBean<Program> csvToBean = new CsvToBeanBuilder(new InputStreamReader(new ByteArrayInputStream(bytes), "EUC-KR"))
                .withType(Program.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .build();

        return csvToBean.parse().stream().filter(program -> {
            program.setRegions(this.regionService.getRegions(program.getServiceArea()));
            return this.programRepository.save(program) != null;
        }).count();
    }

    public List<Program> getProgramsByRegionCode(Region region){
        return programRepository.findByRegions(region);
    }

}
