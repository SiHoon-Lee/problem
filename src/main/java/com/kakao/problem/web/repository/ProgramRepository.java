package com.kakao.problem.web.repository;

import com.kakao.problem.web.domain.Program;
import com.kakao.problem.web.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findByRegions(Region region);

}
