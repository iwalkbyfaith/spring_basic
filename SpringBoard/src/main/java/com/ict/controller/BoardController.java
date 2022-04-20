package com.ict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.PageMaker;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;
import com.ict.service.IBoardService;

import lombok.extern.log4j.Log4j;



// 컨트롤러가 컨트롤러 기능을 할 수 있도록 처리해주세요 (컨트롤러 어노테이션 붙이면 됨)


@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {
	
	// 전체 회원을 보려면, 
		// 1. 회원 목록을 들고 오는 메서드를 실행해야하고
		// 2. 그 메서드를 보유하고 있는 클래스를 선언하고 주입해줘야 합니다.
		// 참고) BoardMapperTests.java
	
	// 04.13 
		// 컨트롤러는 서비스만 호출할 수 있도록 구조를 바꾼다.
		// 서비스를 BoardController 내부에서 쓸 수 있도록 선언/주입해주세요
		// 기존의 @Autowired는 삭제함(주석처리)
		// 인터페이스로 선언을 해야 다형성의 원리에 의해 다른 애들도 사용할 수 있는 것
	
	@Autowired
	private IBoardService service;
	
	
	
//	@Autowired
//	private BoardMapper boardMapper;
	
	
	// ■ 전체 게시글 보기
		// 전체 글 목록을 볼 수 있는 페이지인 boardList.jsp
		// 주소 : /boardList 주소를 get 방식으로 선언해주세요
	
	
		// 04.12 추가 (방법 1.@RequestParam을 이용)
			// defaultValue="1" 문자로 줘야함 (원래 String으로 들어오니까 숫자를 따옴표로 감싸라)
			// @RequestParam(name="사용할변수명", defaultValue="지정하고싶은기본값")
			// 만약 name="page2"라고 적었으면 /boardList?pageNum=10 이런식으로 해야지만 나온다.
//			@GetMapping("/boardList")
//			public String getBoardList(@RequestParam(name="pageNum", defaultValue="1")long pageNum, Model model) {
//				
//				// List<BoardVO> list = boardMapper.getList();로 받아서 list만 바인딩 값으로 넣어도 된다
//				
//				// 바인딩
//				model.addAttribute("boardList", boardMapper.getList(cri));
//				
//				return "boardList";
//			}
	
	
//		// 04.12 추가 (방법 2. @PathVariable 이용)
//			// @PathVariable의 경우 defaultValue를 직접 줄 수 없아나, required=false를 이용해 필수 입력을 안 받게 처리후
//			// 컨트롤러 내부에서 디폴트값을 입력해줄 수 있다.
//			// 기본형 자료는 null을 저장할 수 없기 때문에 wrapper class를 이용해 Long을 선언한다.
//	
//			@GetMapping({"/boardList/{pageNum}", "/boardList"})
//			public String getBoardList(@PathVariable(required = false) Long pageNum, Model model) {
//				
//				if(pageNum == null) {
//					pageNum = 1L;  // Long형은 숫자 뒤에 L을 붙여야 대입된다.
//				}
//		
//		// List<BoardVO> list = boardMapper.getList();로 받아서 list만 바인딩 값으로 넣어도 된다
//		
//		// 바인딩
//		model.addAttribute("boardList", boardMapper.getList(pageNum));
//		
//		return "boardList";
//	}
	
	
//	// 04.12 추가 (방법 3. 새로운 클래스 Criteria를 만들어 사용)
//	@GetMapping("/boardList")
//	public String getBoardList(Criteria cri, Model model) {
//		
//		// List<BoardVO> list = boardMapper.getList();로 받아서 list만 바인딩 값으로 넣어도 된다
//		
//		// 바인딩
//		model.addAttribute("boardList", service.getList(cri));
//		
//		// 페이지네이터 그리기 위해 처리 정보도 같이 전달하기
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);    					          // setter를 이용해 cri입력
//		//pageMaker.setTotalBoard(131);						  // calcData() 메서드까지 호출됨 => prev, next, starPage, endPage 세팅됨
//		// 131 대신 실제로 DB내 글 개수를 받아옴
//		pageMaker.setTotalBoard(service.countPageNum());  // calcData() 메서드까지 호출됨 => prev, next, starPage, endPage 세팅됨
//		model.addAttribute("pageMaker", pageMaker);   		  // 세팅된 pageMaker를 바인딩해줌
//		
//		// 페이징 버튼 눌렀을때 볼드처리하기 위해서 한번 넘겨보기
//		// ★ 넘기지 않아도 .jsp에서 pageMaker.cri.pageNum으로 현재 페이지를 확인할 수 있다.
//		model.addAttribute("currentPage", cri.getPageNum());
//		
//		return "boardList";
//	}
	
	
//	// 04.13 페이지네이션&검색기능을 위해 Criteria -> SearchCriteria로 변경
//	@GetMapping("/boardList")
//	public String getBoardList(SearchCriteria cri, Model model) {
//		
//		// List<BoardVO> list = boardMapper.getList();로 받아서 list만 바인딩 값으로 넣어도 된다
//		
//		// 바인딩
//		model.addAttribute("boardList", service.getList(cri));
//		
//		// 페이지네이터 그리기 위해 처리 정보도 같이 전달하기
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);    					          // setter를 이용해 cri입력
//		//pageMaker.setTotalBoard(131);						  // calcData() 메서드까지 호출됨 => prev, next, starPage, endPage 세팅됨
//		// 131 대신 실제로 DB내 글 개수를 받아옴
//		pageMaker.setTotalBoard(service.countPageNum());  	  // calcData() 메서드까지 호출됨 => prev, next, starPage, endPage 세팅됨
//		model.addAttribute("pageMaker", pageMaker);   		  // 세팅된 pageMaker를 바인딩해줌
//		
//		// 페이징 버튼 눌렀을때 볼드처리하기 위해서 한번 넘겨보기
//		// ★ 넘기지 않아도 .jsp에서 pageMaker.cri.pageNum으로 현재 페이지를 확인할 수 있다.
//		model.addAttribute("currentPage", cri.getPageNum());
//		
//		return "boardList";
//	}
	
	
	// 04.14 검색어에 따라 네비 버튼의 수가 달라지도록 countPageNum에 파라미터 처리
	@GetMapping("/boardList")
	public String getBoardList(SearchCriteria cri, Model model) {
		
		// List<BoardVO> list = boardMapper.getList();로 받아서 list만 바인딩 값으로 넣어도 된다
		
		// 바인딩
		model.addAttribute("boardList", service.getList(cri));
		
		// 페이지네이터 그리기 위해 처리 정보도 같이 전달하기
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);    					        	  // setter를 이용해 cri입력
		//pageMaker.setTotalBoard(131);							  // calcData() 메서드까지 호출됨 => prev, next, starPage, endPage 세팅됨
		// 131 대신 실제로 DB내 글 개수를 받아옴
		pageMaker.setTotalBoard(service.countPageNum(cri));  	  // calcData() 메서드까지 호출됨 => prev, next, starPage, endPage 세팅됨
		model.addAttribute("pageMaker", pageMaker);   		 	  // 세팅된 pageMaker를 바인딩해줌
		
		// 페이징 버튼 눌렀을때 볼드처리하기 위해서 한번 넘겨보기
		// ★ 넘기지 않아도 .jsp에서 pageMaker.cri.pageNum으로 현재 페이지를 확인할 수 있다.
		model.addAttribute("currentPage", cri.getPageNum());
		
		return "board/boardList";
	}
	
	
	
	
	// ■ 상세 글 보기
	@GetMapping("/boardDetail/{bno}")
	public String getBoardDetail(@PathVariable long bno, Model model) {
		
		model.addAttribute("board", service.getBoard(bno));
		
		return "board/boardDetail";
	}

	
	
	
	// ■ 게시글 적재 (폼)
	@GetMapping("/boardInsert")
	public String insertBoardForm() {
		
		return "board/boardForm";
	}
	
	
	// ■ 게시글 적재 (DB에 적재)
	@PostMapping("/boardInsert")
	public String insertBoardToDB(BoardVO vo) {
		
		// 디버깅
		log.info("들어온 BoardVO 데이터 체크 -> " + vo);
		
		// DB에 적재
		service.insert(vo);
		
		return "redirect:/board/boardList";
	}
	
	// ★ 리다이렉트하기 : return "redirect:/주소";
	
	
	
	// ■ 게시글 삭제
	@PostMapping("/boardDelete")
	public String deleteBoard(long bno, SearchCriteria cri, RedirectAttributes rttr) {
		
		// 디버깅
		log.info("들어온 게시글 번호 -> " + bno);
		log.info("/boardDelete에서 받은 정보(서치타입, 키워드) -> " + cri);
		log.info("/boardDelete에서 받은 정보(페이지 번호) -> " + cri.getPageNum());
		
		// 글 삭제
		service.deleteBoard(bno);
		
		// ● 04.15 추가) 검색 타입/키워드를 유지한 채로 들어왔던 페이지로 이동하기
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
			
//			// ○ 이런 방법도 있다 (해시맵을 사용하는 방법. 참고만 하기)
//			// rttr.addAllAttributes(Hashmap);으로 전달시 한 번만 호출해 데이터를 전달할 수 있다.
//			Map<String, Object> parameters = new HashMap<>();
//			parameters.put("pageNum", cri.getPageNum());
//			parameters.put("searchType", cri.getSearchType());
//			parameters.put("keyword", cri.getKeyword());
//			log.info("전달 직전 : " + parameters);
//			// 위에 생성한 맵 자료를 전달
//			rttr.addAllAttributes(parameters);
		
		
		return "redirect:/board/boardList";
	}
	
	
	
	// ■ 게시글 수정 (폼) 
	@PostMapping("/boardUpdateForm")
	public String updateBoard(long bno, Model model) {
		
		// 디버깅
		log.info("들어온 게시글 번호 -> " + bno);
		
		// 해당 게시글 바인딩
		model.addAttribute("board", service.getBoard(bno));
		
		return "board/boardUpdateForm";
	}
	
	
	// ■ 게시글 수정 ( DB에 반영 )
	@PostMapping("/boardUpdate")
	public String updateBoardToDB(BoardVO vo, SearchCriteria cri, RedirectAttributes rttr) {
		
		// 디버깅
		log.info("변경 사항 -> " + vo);
		log.info("/boardUpdate에서 확인하기(서치타입, 키워드) -> " + cri);
		log.info("/boardUpdate에서 확인하기(페이지 번호) -> " + cri.getPageNum());
		
		// 수정
		service.updateBoard2(vo);
		
		// ● 04.15 추가) 리다이렉트시 주소창 뒤에 파라미터 쿼리스트링 형식으로 붙여 보내기 
		// 주소창 뒤에 searchType, keword, pageNum을 붙여서 보내기 위해서 RedirectAttributes 사용
		// rttr.addAttribute("파라미터명", 전달자료);
		// rttr.addFlashAttribute()는 넘어간 페이지에서 파라미터를 쓸 수 있도록 전달하는 것으로 둘의 역할이 다르니 주의할 것.
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/boardDetail/" + vo.getBno();
	}
	
	

}
