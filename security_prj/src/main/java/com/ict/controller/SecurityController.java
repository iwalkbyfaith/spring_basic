package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")		   // /secu라는 경로를 만들어놨기 때문에 반드시 views 아래에도 secu라는 폴더를 생성해야한다.
@Controller
public class SecurityController {  // ◀ 권한에 따라 접속할 수 있는 형태를 차등으로 두기 위한 컨트롤러
	
	
	// void이기 때문에, 접속 주소와 같은 .jsp 파일로 연결된다
	
	
	// 비회원
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all 로직");
	}
	
	// 회원
	@GetMapping("/member")
	public void doMember() {
		log.info("회원들이 접속 가능한 member 로직");
	}
	
	// 운영자
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("운영자만 접속 가능한 admin 로직");
	}

		
}
