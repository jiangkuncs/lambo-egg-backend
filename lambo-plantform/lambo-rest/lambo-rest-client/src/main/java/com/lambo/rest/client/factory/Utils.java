package com.lambo.rest.client.factory;

import com.lambo.common.utils.lang.DateUtils;

/**
 * @ClassName Utils
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/5/17 15:47
 **/
public class Utils {

    public static String convertLike(String value) {
        return "%" + value + "%";
    }

    public static String nextDay(String day,int n) {
        return DateUtils.getNextDay(day,n);
    }

    public static String nextMonth(String month,int n) {
        return DateUtils.getNextMonth(month,n);
    }

    public static String getSamePeriod(String date){
        return DateUtils.getPreYearSamePeriod(date,1);
    }

    public static String getSamePeriod(String date,int n){
        return DateUtils.getPreYearSamePeriod(date,-n);
    }

    public static String getFirstDayOfMonth(String date){
        return DateUtils.getFirstDayOfMonth(date);
    }
}
