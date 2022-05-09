package com.ict.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller 
public class CommonController { // 사용자가 직접적으로 주소를 치는 것이 아니라, 절차를 밟지 않았을 때 억지로 끌려 들어가는 경우에 커먼컨트롤러를 사용한다.

	
	// ■ access가 denied 되었을때
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) { 
		
		log.info("/accessError) 접근 거부 : " + auth);
		// ● 콘솔에 뜨는 메세지 (멤버 계정으로 어드민 페이지 접속한 경우, 콘솔창에 아래와 같이 뜸)
		// INFO : com.ict.controller.CommonController - 접근 거부 : org.springframework.security.authentication.UsernamePasswordAuthenticationToken@ff740c75: Principal: org.springframework.security.core.userdetails.User@bfc28a9a: Username: member; Password: [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: ROLE_MEMBER; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@fffbcba8: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: 3F745BBF1AD63AF79945D25ADA21C4E1; Granted Authorities: ROLE_MEMBER
		
		
		// 바인딩 (그래서 넘어간 페이지에서 EL로 errorMessage를 출력하면 '접근 거부'라는 문자가 출력되는 것)
		model.addAttribute("errorMessage", "접근 거부");
	}
	
	
	
	// ■ 커스텀 로그인
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) { 
		log.info("/customLogin) error 여부 : " + error);
		log.info("/customLogin) logout 여부 : " + logout);
		
		if(error != null) {
			model.addAttribute("error", "로그인 관련 에러입니다. 계정 확인을 다시 해주세요");
		}
		if(logout != null) {
			model.addAttribute("logout", "로그아웃 했습니다");
		}
		
	}
	
	
	
	// ■ 커스텀 로그아웃(GET)
	@GetMapping("/customLogout")	
	public void logoutGet() {
		log.info("로그아웃 폼으로 이동");
	}// void 리턴이기 때문에 customLogout.jsp로 이동한다.
	
	// ■ 커스텀 로그아웃(POST)
	@PostMapping("/customLogout")	
	public void logoutPost() {
		log.info("포스트 방식으로 로그아웃 요청 처리");
		
	}
	
	
	
}
