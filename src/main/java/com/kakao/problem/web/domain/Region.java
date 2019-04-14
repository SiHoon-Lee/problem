package com.kakao.problem.web.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Region {

    @Id
    @GeneratedValue(generator = "region-generator")
    @GenericGenerator(name = "region-generator",
            parameters = @Parameter(name = "prefix", value = "region"),
            strategy = "com.kakao.problem.app.generator.RegionGenerator")
    private String id;

}
