package com.lambo.rest.client.factory;

import com.lambo.common.utils.lang.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BuiltInVariable
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/5/17 17:22
 **/
public class BuiltInVariable {

    private static Logger logger = LoggerFactory.getLogger(BuiltInVariable.class);

    public static Map getBuiltInVariable(){

        Map<String,String> variables = new HashMap<>();
        try{
            variables.put("BUILTIN_USER_ID", (String)SecurityUtils.getSubject().getPrincipal());
            variables.put("BUILTIN_COM_ID", (String)SecurityUtils.getSubject().getSession().getAttribute("COM_ID"));
        }catch(Exception e){
            variables.put("BUILTIN_USER_ID", "test");
            variables.put("BUILTIN_COM_ID", "test");
            logger.error("初始化内置变量出错",e);

        }
        variables.put("BUILTIN_NOW_DATE_YYYYMMDD", DateUtils.getToday());
        variables.put("BUILTIN_NOW_DATE_YYYYMM", DateUtils.getCurMonth());
        variables.put("BUILTIN_NOW_DATE_YYYY", DateUtils.getYear());
        variables.put("BUILTIN_SAME_DATE_YYYYMMDD", DateUtils.getSameDate(DateUtils.getToday()));
        variables.put("BUILTIN_SAME_DATE_YYYYMM", DateUtils.getSameDate(DateUtils.getCurMonth()));
        variables.put("BUILTIN_SAME_DATE_YYYY", DateUtils.getSameDate(DateUtils.getYear()));

        return variables;

    }
}
