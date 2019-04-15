package com.kakao.problem.util;

import com.kakao.problem.app.common.RegionUtil;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class regionUtil_Test {

    @Test
    public void 단일_주소_정규표현식에_대한_검증(){

        List<String> expecteds = Arrays.asList("강원도", "속초", "무쇠점2길");
        List<String> actual = RegionUtil.regExecuter("강원도 속초 무쇠점2길");

        Assert.assertThat(actual, Is.is(expecteds));

    }

    @Test
    public void 필터항목에_대한_검증(){

        String filterWord = "충청북도 월악산, 소백산 일원";

        List<String> expecteds = Arrays.asList("충청북도", "월악산", "소백산");
        List<String> actual =  RegionUtil.regExecuter(filterWord);

        Assert.assertThat(actual, Is.is(expecteds));

        String filterWord2 = "충청북도 월악산, 소백산등";
        actual =  RegionUtil.regExecuter(filterWord2);
        Assert.assertThat(actual, Is.is(expecteds));

        String filterWord3 = "충청북도 월악산, 소백산 634번지";

        actual =  RegionUtil.regExecuter(filterWord3);
        Assert.assertThat(actual, Is.is(expecteds));


        String filterWord4 = "충청북도 월악산, 소백산 사무소";
        actual =  RegionUtil.regExecuter(filterWord4);
        Assert.assertThat(actual, Is.is(expecteds));
    }

}
