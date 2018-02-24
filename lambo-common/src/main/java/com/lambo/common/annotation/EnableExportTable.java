package com.lambo.common.annotation;

import java.lang.annotation.*;

/**
 * 
* @ClassName:  EnableExportTable
* @Description: 导出表格注解
* @author wangjierj
* @date 2018年1月17日 下午3:16:57
*
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableExportTable {
	String value() default "";
}

