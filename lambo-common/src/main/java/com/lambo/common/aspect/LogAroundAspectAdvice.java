package com.lambo.common.aspect;

import com.lambo.common.annotation.LogAround;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author sunzhen
 */
@Component
@Aspect
public class LogAroundAspectAdvice {
    @Around(value = "com.lambo.common.architecture.Architecture.anyMethod() && @annotation(logAround)")
    public Object logAround(ProceedingJoinPoint jp, LogAround logAround) throws Throwable {

        Logger log = LoggerFactory.getLogger(jp.getTarget().getClass());
        String methodName = jp.getSignature().getName();
        String value = logAround.value();

        if (log.isInfoEnabled()) {
            log.info("进入方法：" + value + "----" + methodName);
        }

        long start = System.currentTimeMillis();

        Object o = jp.proceed();

        long end = System.currentTimeMillis();

        if (log.isInfoEnabled()) {
            log.info("方法结束：" + value + "----" + methodName + ",用时：" + (end - start) + "毫秒");
        }

        return o;
    }

}
