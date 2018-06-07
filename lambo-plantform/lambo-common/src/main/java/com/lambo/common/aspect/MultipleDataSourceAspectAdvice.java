package com.lambo.common.aspect;

import com.lambo.common.annotation.DataSource;
import com.lambo.common.db.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MultipleDataSourceAspectAdvice {


	@Around(value = "com.lambo.common.architecture.Architecture.anyMethod() "
			+ "&& @annotation(dataSource)")
    public Object setDataSource(ProceedingJoinPoint jp,DataSource dataSource) throws Throwable {
		
		String ds = dataSource.value();
		if(ds != null && !ds.equals("")){
			DynamicDataSource.setDataSource(ds);
		}
		Object o = null;
		try{
			o = jp.proceed();
		}catch(Throwable t){
			throw t;
		}finally{
			DynamicDataSource.clearDataSource();
		}

		return o;
		
    }
	
	
}
