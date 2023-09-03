package ru.clevertec.bank.aop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CustomPointcut {

    @Pointcut("within(@ru.clevertec.bank.aop.annotation.Logging *) " +
            "&& within(ru.clevertec.bank.service.impl.*ServiceImpl)")
    public void isServiceLayer(){}

}
