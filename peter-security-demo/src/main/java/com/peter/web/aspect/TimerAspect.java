/**
 * 
 */
package com.peter.web.aspect;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author peter
 *
 */
@Aspect
@Component
public class TimerAspect {
    // https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop
	@Around("execution(* com.peter.web.controller.UserController.*(..))")
	public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable {
		System.out.println("time aspect start");
		Object[] args = point.getArgs();
		for (Object arg : args) {
			System.out.println(arg);
		}
		Instant start = Instant.now();
		Object proceed = point.proceed();   
		System.out.println("time aspect 耗时：" + Duration.between(start, Instant.now()).toMillis());
		System.out.println("time aspect end");
		return proceed; // proceed为方法的返回值
	}
}
