package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.ReplyVO;
import com.ict.service.IReplyService;

import lombok.extern.log4j.Log4j;


@Log4j
@RestController
@RequestMapping("/replies") // 접속시 기본 주소에 replies가 붙음.
public class ReplyController {
	
	
	@Autowired
	private IReplyService service;
	
	
	// ■ 댓글 쓰기
	
		// consumes는 이 메서드의 파라미터를 넘겨줄때 어떤 형식으로 넘겨줄지를 설정하는데, 기본적으로는 rest 방식에서는 json을 사용한다.
		// produces는 입력 받은 데이터를 토대로 로직을 실행한 다음 사용자에게 결과로 보여줄 데이터의 형식을 나타낸다. (즉, 리턴자료형이라고 생각하면 됨)
			// MediaType.TEXT_PLAIN_VALUE => 일반 문자열이 나온다
			// PRODUCES에 TEXT_PLAIN_VALUE를 줬으므로 String 리턴
		// 아래 메서드는 json을 사용하므로 무조건 jackson-databind가 추가되어야 함.
		// json = javascript object notation
		// value값에 아무것도 없는 이유 : 교안(08)의 24번 페이지에 그렇게 하기로 정해놓아서
		// String 리턴 : inset 같은 경우는 return값이 없기 때문에 성공이냐 실패냐만 나오면 되어서 String쓴 것.
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
	
	
	
	// ■ 댓글 조회
		// 단일 숫자 데이터 bno만 넣기도하고, PathVariable 어노테이션으로 이미 입력 데이터가 명시되었으므로 consumes은 따로 주지 않아도 된다
		// 레스트에서는 쿼리스트링보다는, 핵심적인 상황에서는 PathVariable을 사용한다. (어쩔수 없이 page 같은거 붙일때도 있음)
		// produces는 댓글 목록이 xml로도, json으로 표현될 수 있도록 아래와 같이 2개를 모두 얹는다.
		// jackson-dataformat-xml을 추가해야 xml도 작동한다.
		// 글번호만 가지고 처리할 수 있으므로 consumes가 필요없음. ( consumes는 자료가 json 같이 여러개 구멍 뚫린 것들을 넣어주는 경우 사용, 메서드 register의 경우 )
			// 쿼리문 자체도 글 번호bno 하나만 뚫려있음을 확인할 수 있다.
		// 이제부터는 기본형으로 ResponseEntity를 기본으로 쓰고(상태코드 날려줘야하니까), 꺽쇠 사이에다가 진짜로 리턴하고 싶은 자료형을 넣으면 됨
	@GetMapping(value="/all/{bno}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno){
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace(); // 콘솔에 에러가 찍힘
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	// ■ 댓글 삭제
		// 일반 방식이 아닌 rest 방식에서는 삭제로직으로 post가 아닌, delete 방식으로 요청하기 때문에 @DeleteMapping을 씁니다
		// 받는 파라미터가 하나이기 때문에 consumes를 쓰지 않는다.
	@DeleteMapping(value="/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		ResponseEntity<String> entity = null;
		
		log.info("댓글 삭제시 들어온 댓글 번호 -> " + rno);
		
		
		try {
			
			service.removeReply(rno);
			log.info("댓글 삭제 try로 진입");
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		}catch(Exception e) {
			log.info("remove에서 뭔가 잘못됨");
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		log.info("try catch 끝");
		
		return entity;
	}
	
	
	
	// ■ 댓글 수정
		// put과 patch를 다 사용할 수 있도록 받아줌.
		// VO는 우선 payload에 적힌 데이터(json)로 받아옵니다
		// @RequestBody가 붙은 VO는 payload에 적힌 데이터를 VO로 환산해서 가져옵니다.
		// 단 댓글 번호는 주소에 기입된 숫자를 자원으로 받아옵니다
		// payload에는 reply만 넣어줘도 되는데 그 이유는 rno는 요청주소로 받아오기 때문입니다.
		// 단, rno를 주소로 받아오는 경우는 아직 replyVO에 rno가 세팅되지 않은 상태이므로 setter로 rno까지 지정해줍니다.
			// 페이로드로 reply만 넘어왔기 때문에 넘어온 replyVO에 rno가 없다는 뜻
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH}, 
							 value="/{rno}",
							 consumes="application/json",
							 produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		
		ResponseEntity<String> entity = null;
		
		try {
			
			// 디버깅(세팅 전)
			log.info("rno 세팅 전 vo -> " + vo );
			// rno 세팅
			vo.setRno(rno);
			// 디버깅 (세팅 후)
			log.info("rno 세팅 후 vo -> " + vo);
			
			
			service.modifyReply(vo);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
			
			
	

}
