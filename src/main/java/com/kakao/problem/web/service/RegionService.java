package com.kakao.problem.web.service;

import com.kakao.problem.web.domain.Program;
import com.kakao.problem.web.domain.Region;
import com.kakao.problem.web.repository.ProgramRepository;
import com.kakao.problem.web.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getRegions(String regionStr){

        List<String> regionList = Region.regionParser(regionStr);

        return regionList.stream().map(s -> {
            Region region = regionRepository.findByRegion(s);
            if(region == null){
                return regionRepository.saveAndFlush(new Region(s));
            }
            return region;
        }).collect(Collectors.toList());
    }

    public Region getRegionCode(String regionCode){
        return regionRepository.findByRegionId(regionCode);
    }

    public Region getRegion(String serviceArea){
        return regionRepository.findByRegion(serviceArea);
    }

}
