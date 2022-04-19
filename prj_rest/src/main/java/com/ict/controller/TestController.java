package com.ict.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.TestVO;

// ★ 클래스 위에 붙인 RequestMapping은 해당 컨트롤러의 공통된 진입 주소를 설정해준다.
	// http://localhost:8181/test/hello
	// /hello에 들어가려면 무조건 그 앞에 /test가 있어야 하는 것.

	// 여기서도 GetMapping, PostMapping 쓸 수 있음

	// jason으로 바뀔 수 있는 유형(네트워크에서 확인 가능) : VO, List<VO>, Map

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Hello";
	}
	
	@RequestMapping("/sendVO")
	public TestVO sendTestVO() {
		TestVO testVO = new TestVO();
		
		testVO.setName("만득");
		testVO.setAge(4);
		testVO.setMno(1);
		
		return testVO;
	}
	
	@RequestMapping("/sendVOList")
	public List<TestVO> sendVOList(){
		
		List<TestVO> list = new ArrayList<>();
		for(int i=0; i <10 ; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i + "만득");
			vo.setAge(20 + i);
			list.add(vo);
		}
		return list;
		
	}

	@RequestMapping("/sendMap")
	public Map<Integer, TestVO> sendMap(){
		
		Map<Integer, TestVO> map = new HashMap<>();
		
		for(int i=0; i <10 ; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i + "꼬마");
			vo.setAge(50 + i);
			map.put(i, vo);
		}
		return map;
		
	}
	
	
	// ■ 강제로 400(BadRequest) 만들기 (인증 실패)
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		
		// return 다음에 new를 쓰는 것을 ▶'익명 클래스'◀라고 부른다. (변수 이름이 없는 클래스)
		// 원래였다면 ResponseEntity<Void> result = new ... 이렇게 해서 result를 return했지만 
		// 코드 줄 수를 줄이기 위한 리팩토링 작업에서 '익명 클래스'를 사용해서 이렇게 바로 리턴시킬 수 있음
		
		return
				new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	} 

	
	// ■ 사용자가 어떻게 에러 냈는지, 그 데이터와 에러원인을 함께 보냄
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<TestVO>> sendListNot(){
		List<TestVO> list = new ArrayList<>();
		
		for(int i=0; i <10 ; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i + "카리나");
			vo.setAge(20 + i);
			list.add(vo);
		}
		
		return 
				new ResponseEntity<List<TestVO>>(
					list, HttpStatus.NOT_FOUND);
	}
	
	
	
}
