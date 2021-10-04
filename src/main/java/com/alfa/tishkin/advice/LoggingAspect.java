package com.alfa.tishkin.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {
    private final static Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.alfa.tishkin.service.impl.CurrencyServiceImpl.*(..))")
    public void currencyActionMethods() {}

    @AfterReturning(value = "currencyActionMethods()", returning = "response")
    public void logGetMethods(JoinPoint joinPoint, Object response) {
        logger.info(joinPoint.getSignature().getName()
        + " args " + Arrays.toString(joinPoint.getArgs())
        + " response " + response.toString());
    }

    @AfterThrowing(value = "currencyActionMethods()", throwing = "exception")
    public void logExceptions(JoinPoint joinPoint, RuntimeException exception) {
        logger.error(joinPoint.getSignature().getName()
        + " args " + Arrays.toString(joinPoint.getArgs())
        + " exception details " + exception.getMessage());
    }
}
