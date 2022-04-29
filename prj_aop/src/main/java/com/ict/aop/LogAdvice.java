package com.ict.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;


@Aspect
@Log4j
@Component
public class LogAdvice {
	
	@Before("execution(* com.ict.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=======================");
	}
	
	
	// @Before 옆의 괄호안의 문장을 '표현식' 이라고 함
		// *      :  접근 제한자- 와일드카드 (뭐든 오케이)
		// 패키지   : 어느 패키지/어떤 서비스. 
		//           SampleService로 시작하면 오케이. 뒤에 Impl이 들어가든 뭐가 들어가든 상관 없다는 것
		// .      : 메서드명
		// 
	
	
	// logBefore() 옆에 화살표 보면 advises com.ict.service.SampleServiceImpl.doAdd(String, String) 라고 적혀있음
		// '표현식에 부합하는 메서드가 얘' 라는 뜻. 누구한테 적용을 해 줄것이냐고 해석하면 됨.
		// SapleServiceImpl에 가서 봐도 화살표가 적혀있음 ( 보조기능 적용한 쪽/ 적용된 쪽, 양쪽 모두에 뜬다 )

}
