package com.kakao.problem.web.domain;

import com.kakao.problem.app.common.RegionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue(generator = "region-generator")
    @GenericGenerator(name = "region-generator",
            parameters = @Parameter(name = "prefix", value = "region"),
            strategy = "com.kakao.problem.app.generator.RegionGenerator")
    private String regionId;

    private String region;

    public Region(String region) {
        this.region = region;
    }

    public static List<String> regionParser(String region){

        List<String> regionList = RegionUtil.regExecuter(region);
        return regionList;
    }

}
