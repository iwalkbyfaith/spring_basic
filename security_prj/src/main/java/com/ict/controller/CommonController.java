package com.ict.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {	 	// ◀ access가 denied 되었을때 사용할 공통 컨트롤러

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		
		log.info("접근 거부 : " + auth);
		// ● 콘솔에 뜨는 메세지 (멤버 계정으로 어드민 페이지 접속한 경우, 콘솔창에 아래와 같이 뜸)
		// INFO : com.ict.controller.CommonController - 접근 거부 : org.springframework.security.authentication.UsernamePasswordAuthenticationToken@ff740c75: Principal: org.springframework.security.core.userdetails.User@bfc28a9a: Username: member; Password: [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: ROLE_MEMBER; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@fffbcba8: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: 3F745BBF1AD63AF79945D25ADA21C4E1; Granted Authorities: ROLE_MEMBER
		
		
		// 바인딩 (그래서 넘어간 페이지에서 EL로 errorMessage를 출력하면 '접근 거부'라는 문자가 출력되는 것)
		model.addAttribute("errorMessage", "접근 거부");
	}
}
