package com.zhytnik.library.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ServiceLogger {
    private static Logger logger = Logger.getLogger("Service");

    @AfterReturning(
            pointcut = "execution(public * com.zhytnik.library.service.AbstractService.*(..)))",
            returning = "result")
    public void methodsWithResult(JoinPoint joinPoint, Object result) {
        //noinspection StringBufferReplaceableByString
        String msg = new StringBuilder().append(joinPoint.getSignature().toShortString()).
                append("(").append(Arrays.toString(joinPoint.getArgs())).
                append(") return ").append(result).toString();
        logger.log(Level.INFO, msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.zhytnik.library.service.AbstractService.*(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        //noinspection StringBufferReplaceableByString
        String msg = new StringBuilder().append(joinPoint.getSignature().toShortString()).
                append("(").append(Arrays.toString(joinPoint.getArgs())).
                append(") throw ").append(error).toString();
        logger.log(Level.WARN, msg);
    }
}
