package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.domain.BoardVO;
import com.ict.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;



// 컨트롤러가 컨트롤러 기능을 할 수 있도록 처리해주세요 (컨트롤러 어노테이션 붙이면 됨)


@Controller
@Log4j
public class BoardController {
	
	// 전체 회원을 보려면, 
		// 1. 회원 목록을 들고 오는 메서드를 실행해야하고
		// 2. 그 메서드를 보유하고 있는 클래스를 선언하고 주입해줘야 합니다.
		// 참고) BoardMapperTests.java
	
	@Autowired
	private BoardMapper boardMapper;
	
	
	// ■ 전체 게시글 보기
		// 전체 글 목록을 볼 수 있는 페이지인 boardList.jsp
		// 주소 : /boardList 주소를 get 방식으로 선언해주세요
	@GetMapping("/boardList")
	public String getBoardList(@RequestParam(defaultValue="1")long pageNum, Model model) {
		
		
		// List<BoardVO> list = boardMapper.getList();로 받아서 list만 바인딩 값으로 넣어도 된다
		
		// 바인딩
		model.addAttribute("boardList", boardMapper.getList(pageNum));
		
		return "boardList";
	}
	
	
	// ■ 상세 글 보기
	@GetMapping("/boardDetail/{bno}")
	public String getBoardDetail(@PathVariable long bno, Model model) {
		
		model.addAttribute("board", boardMapper.getBoard(bno));
		
		return "boardDetail";
	}
	
	
	
	
	// ■ 게시글 적재 (폼)
	@GetMapping("/boardInsert")
	public String insertBoardForm() {
		
		return "boardForm";
	}
	
	
	// ■ 게시글 적재 (DB에 적재)
	@PostMapping("/boardInsert")
	public String insertBoardToDB(BoardVO vo) {
		
		// 디버깅
		log.info("들어온 BoardVO 데이터 체크 -> " + vo);
		
		// DB에 적재
		boardMapper.insert(vo);
		
		return "redirect:/boardList";
	}
	
	// ★ 리다이렉트하기 : return "redirect:/주소";
	
	
	
	// ■ 게시글 삭제
	@PostMapping("/boardDelete")
	public String deleteBoard(long bno) {
		
		// 디버깅
		log.info("들어온 게시글 번호 -> " + bno);
		
		// 글 삭제
		boardMapper.deleteBoard(bno);
		
		return "redirect:/boardList";
	}
	
	
	
	// ■ 게시글 수정 (폼)
	@PostMapping("/boardUpdateForm")
	public String updateBoard(long bno, Model model) {
		
		// 디버깅
		log.info("들어온 게시글 번호 -> " + bno);
		
		// 해당 게시글 바인딩
		model.addAttribute("board", boardMapper.getBoard(bno));
		
		return "boardUpdateForm";
	}
	
	
	// ■ 게시글 수정 ( DB에 반영 )
	@PostMapping("/boardUpdate")
	public String updateBoardToDB(BoardVO vo) {
		
		// 디버깅
		log.info("변경 사항 -> " + vo);
		
		// 수정
		boardMapper.updateBoard2(vo);
		
		return "redirect:/boardList/" + vo.getBno();
	}
	
	

}
