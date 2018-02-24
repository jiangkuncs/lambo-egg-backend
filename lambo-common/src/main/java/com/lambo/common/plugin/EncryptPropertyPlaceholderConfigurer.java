package com.lambo.common.plugin;

import com.lambo.common.util.AESUtil;
import com.lambo.common.util.SystemVariableUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.regex.*;

/**
 * 支持加密配置文件插件
 * @author sunzhen
 * @date 2017/11/14
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    /**
     * 匹配${AAA_BBB}或者${AAA_BBB:xxx}形式字符串
     */
    private static final Pattern SYSTEM_VARIABLE_PATTERN = Pattern.compile("\\$\\{[A-Z]+_*[A-Z]+(:\\w+)*}");

    private String[] propertyNames = {
            "master.jdbc.password", "slave.jdbc.password", "generator.jdbc.password", "master.redis.password"
    };

    /**
     * 解密指定propertyName的加密属性值
     * 解析含有${}格式的propertyValue值,将其以环境变量替代
     *
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        //解密指定propertyName的加密属性值
        for (String p : propertyNames) {
            if (p.equalsIgnoreCase(propertyName)) {
                propertyValue = AESUtil.AESDecode(propertyValue);
            }
        }
        //解析含有${}格式的propertyValue值,将其以环境变量替代
        propertyValue = SystemVariableUtil.convertSystemVariable(propertyValue);

        return super.convertProperty(propertyName, propertyValue);
    }
}
