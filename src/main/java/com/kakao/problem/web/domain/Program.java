package com.kakao.problem.web.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Program {

    @Id
    @Column(unique = true)
    @CsvBindByPosition(position = 0)
    private String programId;

    @CsvBindByPosition(position = 1)
    private String programName;

    @CsvBindByPosition(position = 2)
    private String categoryName;

    @Transient
    @CsvBindByPosition(position = 3)
    private String serviceArea;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Region> regions;

    @CsvBindByPosition(position = 4)
    private String programInfo;

    @Column(length = 500)
    @CsvBindByPosition(position = 5)
    private String programContent;

}
