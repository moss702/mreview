package com.ikkikki.mreview.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect
@Log4j2
public class LogAspect {
  @Before("execution(* *..*.ReviewController.*(..))") //before는 반환안함. around는 반드시 반환해야 함
  public void before(JoinPoint joinPoint){
    log.info("-------------------------" + joinPoint.getSignature().getName()); //signature : 메서드의 선언부
    Arrays.stream(joinPoint.getArgs()).forEach(log::info);
  }

}
