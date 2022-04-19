package com.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.ReplyVO;
import com.ict.service.IReplyService;



@RestController
@RequestMapping("/replies") // 기본적으로 replies가 붙음.
public class ReplyController {
	
	
	@Autowired
	private IReplyService service;
	
	
	// consumes는 이 메서드의 파라미터를 넘겨줄때 어떤 형식으로 넘겨줄지를 설정하는데, 기본적으로는 rest 방식에서는 json을 사용한다.
	// produeces는 입력 받은 데이터를 토대로 로직을 실행한 다음 사용자에게 결과로 보여줄 데이터의 형식을 나타낸다. (즉, 리턴자료형이라고 생각하면 됨)
	// MediaType.TEXT_PLAIN_VALUE => 일반 문자열이 나온다
	// 아래 메서드는 json을 사용하므로 무조건 jackson-databind가 추가되어야 함.
	// json = javascript object notation
	// PRODUCES에 TEXT_PLAIN_VALUE를 줬으므로 String 리턴
	// value값에 아무것도 없는 이유 : 교안(08)의 24번 페이지에 그렇게 하기로 정해놓아서
	@PostMapping(value="", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	// produces에 TEXT_PLAIN_VALUE를 줬으므로 결과 코드와 문자열을 넘김
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		// rest컨트롤러에서 받는 파라미터 앞에 @RequestBody 어노테이션이 붙어야 consumes와 연결됨
		// rest컨트롤러에서 받는 파라미터 앞에 @RequestBody 어노테이션이 붙어야 형식에 맞는 json 데이터를 vo에 매칭시켜준다.
		
		
		// 에러 나는 경우와 안 나는 경우를 대비해서 빈 ResponseEntity를 생성
		ResponseEntity<String> entity = null;
		try {
			// 입력 로직 실행 후
			service.addReply(vo);
			// 문제 없이 다음 줄로 넘어왔다면 성공했을때 화면에 띄울 ResponseEntity 생성
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			

		}catch(Exception e) {
			// catch로 넘어왔다라는건 글쓰기 로직에 문제가 생긴 상황
			// 에러나면 에러 메세지와 함께 ResponseEntity 생성
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 위의 try블럭이나 catch 블럭에서 얻어온 entity 변수 리턴
		return entity;
	}
			
			
	

}
