package com.rahul.rexptrack.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.rahul.rexptrack.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startedAt = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startedAt;
            String methodName = joinPoint.getSignature().toShortString();
            log.info("Method {} executed in {}ms", methodName, executionTime);
        }
    }
}
