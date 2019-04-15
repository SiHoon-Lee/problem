package com.kakao.problem.web.repository;

import com.kakao.problem.web.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {

    Region findByRegion(String region);

    Region findByRegionId(String regionId);

}
