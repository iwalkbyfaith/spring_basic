package com.ict.aop;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;


@Aspect
@Log4j
@Component
public class LogAdvice {
	
	// ■ 메서드 시작 전 (에러와 상관없이 출력)
	@Before("execution(* com.ict.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=======================");
	}
	
	// ■ 메서드 시작 후 (에러와 상관없이 출력)
	@After("execution(* com.ict.service.SampleService*.*(..))")
	public void logAfter() {
		log.info("=======================");
	}
	
	
	
	// @Before 옆의 괄호안의 문장을 '표현식' 이라고 함
		// *      :  접근 제한자- 와일드카드 (뭐든 오케이)
		// 패키지   : 어느 패키지/어떤 서비스. 
		// .SampleService* : SampleService로 시작하면 오케이. 뒤에 Impl이 들어가든 뭐가 들어가든 상관 없다는 것
		// .*      : . 뒤에는 메서드명
		// (..)    : 파라미터 ??
	
	
	// logBefore() 옆에 화살표 보면 advises com.ict.service.SampleServiceImpl.doAdd(String, String) 라고 적혀있음
		// '표현식에 부합하는 메서드가 얘' 라는 뜻. 누구한테 적용을 해 줄것이냐고 해석하면 됨.
		// SapleServiceImpl에 가서 봐도 화살표가 적혀있음 ( 보조기능 적용한 쪽/ 적용된 쪽, 양쪽 모두에 뜬다 )

	
	
	@Before("execution(* com.ict.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1: " + str1);
		log.info("str2: " + str2);
	}
	
	// .doAdd(String, String) : 메서드명이 doAdd이어야만 실행을 해주겠다. String을 두개를 받는다
	// args(str1, str2) : 그 String의 파라미터의 이름은 str1, str2 이어야한다!?
	
	// 메서드 doAdd의 목표는 들어온 값을 더해주는 것(핵심로직)이지, 그 값이 무엇인지 확인(보조로직)하는 것이 아니다
	// 따라서 핵심로직(SampleServiceImpl)과 보조로직(LogAdvice)의 분리가 일어난 것
	
	
	// ■ 정상적으로 실행되지 않았을때만 작동함
	@AfterThrowing(pointcut = "execution(* com.ict.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		log.info("Exception 발생 .. !!!");
		log.info("exception : " + exception);
	}
	
	// pointcut : 실제로 적용될 대상
	// throwing="exeption" : 어떤 종류의 예외에 적용할지 (이 경우는 '모든' 예외)
	// 화살표 생긴 것도 다름 (advises com.ict.service.SampleServiceImpl.doAdd(String, String))
	
	
	
	// ■ 메서드 실행 시간(후-전) : Before + After의 역할이 동시에 적용된 느낌
		// 1) 메서드를 실행하기 전 실행할 부분
		// 2) 메서드(핵심로직) 실행
		// 3) 메서드를 실행한 후 실행할 부분 으로 나뉨
	@Around("execution(* com.ict.service.SampleService*.*(..))")
						  // 앞으로 실행될 메서드(pointcut)에 대한 정보를 pjp에 저장중
	public Object logTime(ProceedingJoinPoint pjp) {

		// ● 시작 시간 저장 ( 메서드 실행 직전 시간 저장 ) : @Before의 역할을 함
		long start = System.currentTimeMillis();
		
		log.info("Target : " + pjp.getTarget());				// 해당 메서드 명칭
		log.info("param: " + Arrays.toString(pjp.getArgs()));	// 해당 메서드 파라미터들
		
		Object result = null;
		
		// ■■■■■■■■■■■■■■■■■■■■■■■■ ▲ 이전까지 핵심 로직은 실행 안됨  ▲ ■■■■■■■■■■■■■■■■■■■■■■■■
		try {
			// ★ '핵심 로직을 실행하라'는 명령어 ★ (실제 메서드(ex. doAdd() )가 실행되는 시점임)
			result = pjp.proceed();
			
		// ■■■■■■■■■■■■■■■■■■■■■■■■ ▼ 핵심 로직 실행 후 실행할 코드들 ▼  ■■■■■■■■■■■■■■■■■■■■■■■■
		}catch(Throwable e) {
			// 예외 발생시 실행
			e.printStackTrace();
		}
		
		// ● 종료 시간 저장 (메서드 실행이 모두 끝난 직후 시간 저장) : @After의 역할을 함
		long end = System.currentTimeMillis();
		
		// 소요시간 계산해 로그에 찍기 (단위 : 밀리초)
		log.info("TIME : " + (end - start));
		
		return result;
	}
	
	
	
	
	
	
}
