package com.example.mybatis.demomybatis;

import cn.hutool.core.date.DateUtil;
import com.example.mybatis.demomybatis.utils.DateFormatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
 */
@SpringBootTest
public class DateFormatUtilTest {

    @Test
    public String testDateFormat(){
        System.out.println("l");
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        return DateFormatUtil.formatDate(date);
    }
}
