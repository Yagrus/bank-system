package ru.clevertec.bank.aop.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger();

    @Around(value = "ru.clevertec.bank.aop.pointcut.CustomPointcut.isServiceLayer()")
    public Object aroundAllService(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String nameMethod = methodSignature.getName();
        String nameClass = joinPoint.getSourceLocation().getWithinType().getSimpleName();
        Object[] args = joinPoint.getArgs();

        log.info("Class {}: method {} accepted parameters - {}",nameClass, nameMethod, args);

        try {
            Object result = joinPoint.proceed(args);
            log.info("Class {} {} object - {}",nameClass, nameMethod,  args);

            log.info("Class {}: method - {} returned parameters - {}",nameClass, nameMethod, result);

            return result;
        } catch (Throwable e) {
            log.error("Error in {}: " + e.getMessage(), nameClass);
            throw e;
        }
    }
}
