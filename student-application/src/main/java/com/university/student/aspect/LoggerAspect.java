package com.university.student.aspect;

import jakarta.persistence.criteria.Join;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Aspect
public class LoggerAspect {

    private final Logger log = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("execution(* com.university.student.service.StudentService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("{} method started", joinPoint.getSignature().toString());
        Instant startTime = Instant.now();
        Object result = joinPoint.proceed();
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(finishTime, startTime).toMillis();
        log.info("Time for execution of this method is {}", timeElapsed);
        log.info("{} Method Ended", joinPoint.getSignature().toString());
        return result;
    }

    @Around("annotation(* com.university.student.annotation.LoggerAspect)")
    public Object logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("{} method started", joinPoint.getSignature().toString());
        Instant startTime = Instant.now();
        Object result = joinPoint.proceed();
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(finishTime, startTime).toMillis();
        log.info("Time for execution of this method is {}", timeElapsed);
        log.info("{} Method Ended", joinPoint.getSignature().toString());
        return result;
    }

    @AfterThrowing(value = "execution(* com.university.student.service.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
        log.error("Exception thrown in {} method and exception is {}", joinPoint.getSignature().toString(), ex.getMessage());
    }

//    @AfterReturning(value = "execution(* com.university.student.service.*.*(..))",returning = "returnValue")
//    public void logReturning(JoinPoint joinPoint, Object returnValue){
//        log.info("{} Method is successfully completed and Returning value is {}", joinPoint.getSignature(), returnValue.toString());
//    }

}
