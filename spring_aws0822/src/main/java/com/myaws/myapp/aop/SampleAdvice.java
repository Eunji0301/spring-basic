package com.myaws.myapp.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
// @Sl4j // 롬복 라이브러리 추가할 떄 사용
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	@Before("execution(* com.myaws.myapp.service.BoardService*.*(..))")
	public void startLog() {
		logger.info("------------------------------");
		logger.info("AOP 로그 테스트중입니다.");
		logger.info("------------------------------");
		// System.out.println("테스트");
	}
}
