package com.kakao.problem.web.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Program {

    @Id
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String programName;

    @CsvBindByPosition(position = 2)
    private String categoryName;

    @CsvBindByPosition(position = 3)
    private String serviceArea;

    @CsvBindByPosition(position = 4)
    private String programInfo;

    @Column(length = 500)
    @CsvBindByPosition(position = 5)
    private String programContent;


    public Program(String id, String programName, String categoryName, String serviceArea, String programInfo, String programContent) {
        this.id = id;
        this.programName = programName;
        this.categoryName = categoryName;
        this.serviceArea = serviceArea;
        this.programInfo = programInfo;
        this.programContent = programContent;
    }

}
