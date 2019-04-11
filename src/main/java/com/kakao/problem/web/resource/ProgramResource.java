package com.kakao.problem.web.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/program")
public class ProgramResource {

    @PostMapping("/csv/bulk")
    public void saveBulk(@RequestParam("file") MultipartFile file){

    }

}
