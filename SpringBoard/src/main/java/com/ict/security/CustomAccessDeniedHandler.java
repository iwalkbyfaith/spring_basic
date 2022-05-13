package com.ict.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

@Log4j	// ▼ 커스터마이징을 할 경우는 AccessDeniedHandler 인터페이스를 직접 오버라이딩해 구현해야함.
public class CustomAccessDeniedHandler implements AccessDeniedHandler {		// xxx핸들러 뜻 => 특정한(xxx) 상황이 터졌을때 걔를 해결해주는 용도
	
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		// 멤버 계정으로 어드민 페이지에 접근한 경우 
		
			// 1. 로그 찍고 (에러 등급의 로그만 출력해줌)
			log.error("커스텀 접근 거부 핸들러 실행");
			log.error("/accessError 페이지로 리다이렉트");
			
			// 2. 리다이렉트 시킴
			response.sendRedirect("/accessError");
		
	}

}
