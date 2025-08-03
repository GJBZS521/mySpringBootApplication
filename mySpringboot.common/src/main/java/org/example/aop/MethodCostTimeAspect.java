package org.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodCostTimeAspect {

    @Pointcut("@annotation(org.example.annotation.MethodCostTimeAnnotation)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object MethodCostTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println("方法执行时间为: " + duration + "ms");
        return result;
    }
}
