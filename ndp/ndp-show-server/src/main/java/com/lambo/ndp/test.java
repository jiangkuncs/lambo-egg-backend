package com.lambo.ndp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {


        String serviceName = "org.loushang.bwf.api.bizWfQueryService";
        String methodName = "getDaiBanTaskCountByUserId";
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(day).toString());
    }
}
