package com.lambo.common.architecture;

import org.aspectj.lang.annotation.Pointcut;

public class Architecture {

    @Pointcut("execution(* *(..))")
    public void anyMethod(){

    }

    @Pointcut("execution(public * *(..))")
    public void anyPublicMethod(){

    }
}