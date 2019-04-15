package com.kakao.problem.app.common;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegionUtil {

    private final static String REGION_REG = "(([가-힣]{2,10})+(\\d+(로|길))|([가-힣]{2,10}))";

    private final static List<String> FILTER_WORD = Arrays.asList(
            "등",
            "일원",
            "번지",
            "사무소"
    );


    public static List<String> regExecuter(String region){

        Pattern infoPattern = Pattern.compile(REGION_REG);
        Matcher matcher = infoPattern.matcher(region);

        List<String> regions = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group(1);

            for ( int i = 0; i < FILTER_WORD.size(); i++ ){
                if(word.contains(FILTER_WORD.get(i))){
                    word = word.replace(FILTER_WORD.get(i), "");
                }
            }

            if(!"".equals(word)){
                regions.add(word);
            }
        }

        return regions;
    }

}
