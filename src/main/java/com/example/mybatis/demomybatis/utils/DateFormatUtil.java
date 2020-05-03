package com.example.mybatis.demomybatis.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;
import java.util.Optional;

/**
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
 */
public class DateFormatUtil {

    private static final String DATE_INFO = "{}月{}日 {}";
    private static final String DATE_TODAY_INFO = "今天 {}";

    public static String formatDate(Date date){
        Date now = DateUtil.date();
        DateTime dateTime = new DateTime(date);
        int day = dateTime.dayOfMonth();
       // System.out.println(day);
        int month = dateTime.month()+1;
       // System.out.println(month);
        String format = DateUtil.formatTime(date);
       // System.out.println(format);
        long between = DateUtil.between(date, now, DateUnit.DAY);
        // System.out.println(between);
        return Optional.of(between)
                .filter(u->between>0)
                .map(u->StrUtil.format(DATE_INFO,month,day,format))
                .orElseGet(()->StrUtil.format(DATE_TODAY_INFO,format));
    }

}
