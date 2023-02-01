package ru.geek.data.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("@within(ru.geek.data.annotation.LogExecutionTime)")
    public void pointcutForAnnotatedClasses(){}

    @Pointcut("@annotation(ru.geek.data.annotation.LogExecutionTime)")
    public void pointcutForAnnotatedMethods() {}

    @Pointcut("pointcutForAnnotatedClasses() || pointcutForAnnotatedMethods()")
    public void targetPointcut() {}

    @Around("targetPointcut()")
    public Object aroundAnnotatedMethodsPointcut(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().getName();

        long start = System.currentTimeMillis();
        Object o = pjp.proceed();
        long time = System.currentTimeMillis() - start;

        log.info("Method " + methodName + " took {} ms for execution", time);
        if(o != null){
            return o;
        }
        return null;
    }



}
