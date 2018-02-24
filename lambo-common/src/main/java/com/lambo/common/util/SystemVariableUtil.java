package com.lambo.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配${AAA_BBB}或者${AAA_BBB:xxx}形式字符串,用到环境变量中找AAA_BBB,将字符串转化为对应的值,默认为xxx
 *
 * @author sun_zhen
 */
public class SystemVariableUtil {

    /**
     * 匹配${AAA_BBB}或者${AAA_BBB:xxx}形式字符串
     */
    private static final Pattern SYSTEM_VARIABLE_PATTERN = Pattern.compile("\\$\\{[A-Z]+_*[A-Z]+(:\\w+)*}");

    public static String convertSystemVariable(String variableStr) {
        //解析含有${}格式的propertyValue值,将其以环境变量替代
        Matcher matcher = SYSTEM_VARIABLE_PATTERN.matcher(variableStr);
        while (matcher.find()) {
            int start = matcher.start(), end = matcher.end();
            String matchStr = variableStr.substring(start, end);
            String systemVariableKey = "", defaultValue = "";
            if (matchStr.indexOf(":") > -1) {
                systemVariableKey = matchStr.split(":")[0].replace("${", "");
                defaultValue = matchStr.split(":")[1].replace("}", "");
            }
            String systemVariableValue = System.getenv().get(systemVariableKey);
            if (null != systemVariableValue) {
                variableStr = matcher.replaceAll(systemVariableValue);
            } else {
                variableStr = matcher.replaceAll(defaultValue);
            }
        }
        return variableStr;
    }

    //测试方法
    public static void main(String[] args){
        System.out.println(convertSystemVariable("${REDIS_SERVER:rdserver}"));
    }
}
