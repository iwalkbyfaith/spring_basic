package com.ict.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j		 // ▼ 로그인 성공 처리용 핸들러
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler { 

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// ■ 로그인 성공시 어떤 권한인지 체크하기 위해 부여받은 권한(들) 불러오기
			// ROLE_ADMIN의 경우는 ROLE_MEMBER가 함께 부여되기 때문에, 경우에 따라 권한이 여럿일 수 있음
		log.warn("■■■■■■■■■■■■■■■■ ▼ ■■■■■■■■■■■■■■■■");
		log.warn("로그인 성공");
		List<String> roleList = new ArrayList<>();
		
		
		// ■ 얻어온 authorities를, 향상된 for문으로 roleList에 하나씩 넣어줌
		for(GrantedAuthority role : authentication.getAuthorities()) { // getter에 s가 붙어 있으면 모든 자료를 '다 가져온다'라고 보면 됨
			roleList.add(role.getAuthority());
		}
		
		// ■ roleList에 포함된 권한을 통해 로그인 계정의 권한에 따라 처리
			// ★ return 구문도 각 if마다 붙여줘야 함 (아니면 리다이렉트가 중복 호출 됨)
		log.warn("부여받은 권한들" + roleList);
		log.warn("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		
		if(roleList.contains("ROLE_ADMIN")) {
			response.sendRedirect("/secu/admin");		// 주소는 추후에 로그인된 유저가 갈 페이지를 만들고 거기로 연결.
			return;
		}
		if(roleList.contains("ROLE_MEMBER")) {
			response.sendRedirect("/secu/member");
			return;
		}
		
		response.sendRedirect("/");						// 로그인 안 한 유저가 갈 페이지
		
		
	}

}
