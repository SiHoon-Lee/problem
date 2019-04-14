package com.kakao.problem.web.repository;

import com.kakao.problem.web.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}
