package com.ict.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.domain.AuthVO;
import com.ict.domain.MemberVO;
import com.ict.service.SecurityService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")		   // /secu라는 경로를 만들어놨기 때문에 반드시 views 아래에도 secu라는 폴더를 생성해야한다.
@Controller
public class SecurityController {  // ◀ 권한에 따라 접속할 수 있는 형태를 차등으로 두기 위한 컨트롤러
	
	// void이기 때문에, 접속 주소와 같은 .jsp 파일로 연결된다
	
	// ■ 05.11 회원가입 처리
		
		@Autowired
		private SecurityService service;
		
		@Autowired
		private PasswordEncoder pwen;
	
	
	
	// ■ 비회원
	@PreAuthorize("permitAll") // 05.11 어노테이션 추가 (안 붙여도 되지만 코드 가독성 측면에서 추가)
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all 로직");
	}
	
	// ■ 회원
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") // 05.11 어노테이션 추가
	@GetMapping("/member")
	public void doMember() {
		log.info("회원들이 접속 가능한 member 로직");
	}
	
	// ■ 운영자
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')") // 05.11 어노테이션 추가
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("운영자만 접속 가능한 admin 로직");
	}
	
	
	// ■ 05.11 회원가입 창 get
	@PreAuthorize("permitAll")
	@GetMapping("/join")
	public void joinForm() {
		log.info("회원가입창 접속");
	}
	
	// ■ 05.11 회원가입 창 post
	@PreAuthorize("permitAll")
	@PostMapping("/join")
	public void join(MemberVO vo, String[] role) { 
		log.info("가입시 받는 데이터들 : " + vo);
		
		// ● 비밀번호 암호화 (평문으로 되어있던 암호를 encode를 사용하여 암호화를 시킨다)
			
			// 1. getter로 vo에 들어온 비밀번호를 조회
			String beforeCrpw = vo.getUserPw();
			log.info("암호화 전 비밀번호-> " + beforeCrpw);
			
			// 2. setter를 사용해 암호화된 비밀번호를 vo에 넣은 후, getter를 사용해 조회한다.
			vo.setUserPw(pwen.encode(beforeCrpw));
			log.info("암호화 후 비밀번호-> " + vo.getUserPw());
		
		// ● 권한 넣어주기
		log.info("▼ 사용자가 선택한 권한 목록 : " + role);
			// role은 참조형 변수여서 반복문으로 풀어야함
			for(String r : role) {
				log.info(r);
			}
			
			// setter를 이용해 vo의 authList에 빈 리스트를 만들어주고
			// 그 리스트에다 들어온 권한을 반복문을 이용해서 빈 VO에 순차적으로 넣어줌
			// 권한 정보 + 어떤 유저가 가지는지도 넣어줘야함. (setAuth, setUserid 둘 다 실행)
		
			// 1. null 상태인 authList에 빈 ArrayList를 먼저 배정
			vo.setAuthList(new ArrayList<AuthVO>());
			
			// 2. authList는 List<authList>이므로 권한 개수에 맞게 넣어줘야함.
			for(int i=0; i < role.length; i++) {
				vo.getAuthList().add(new AuthVO());					 // AuthVO를 하나 만들고
				vo.getAuthList().get(i).setAuth(role[i]);			 // 
				vo.getAuthList().get(i).setUserId(vo.getUserId());	 // 받아온 vo에 있던 아이디를 넣어줌
			}
			log.info(vo.getAuthList());
		
		// ● 서비스 호출 (vo를 두 개의 매퍼 메서드를 넣어주는 서비스)
		service.insertMember(vo);
	}

		
}
