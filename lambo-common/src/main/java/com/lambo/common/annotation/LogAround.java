package com.lambo.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: LogForMethod 
* @Description: 日志注解。用于输出方法的进入和结束日志
* @author sun_zhen
* @date 2016年3月25日 下午2:02:57 
*
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAround {
	String value() default "";
}

