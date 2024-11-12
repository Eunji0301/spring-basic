package com.myaws.myapp.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardTimeCheckAdvice {
	private static final Logger logger = LoggerFactory.getLogger(BoardTimeCheckAdvice.class);
	
	@Around("execution(* com.myaws.myapp.service.BoardService*.*(..))")
	public Object timelog(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		
		logger.info("시작하는 AOP");
		logger.info(Arrays.toString(pjp.getArgs()));
		long startTime = System.currentTimeMillis();
		
		result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("끝나는 AOP");
		
		long durTime = endTime - startTime;
		logger.info(pjp.getSignature().getName() + " 걸린시간 : " + durTime);
		
		return result;
	}
	
}
