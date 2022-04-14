package com.ict.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.domain.BoardVO;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired // 어노테이션으로 처리하면 빈즈그래프에는 안보임(하지만 있는 상태)
	private BoardMapper boardMapper;
	
	
	
//	@Test // 모든 글 리스트
//	public void getBoardList(long pageNum) {
//		log.info("getBoardList() 실행");
//		log.info(boardMapper.getList(pageNum));
//		
//		// 이렇게 해도 됨
//		// List<BoardVO> result = boardMapper.getList();
//		// log.info("저장된 게시물 정보 : " + result);
//	}
	
	//@Test // 데이터 적재
	public void testInsert() {
	
		// vo를 입력받는 insert 메서드 특성상, title, content, writer가 채워진 vo를 먼저 생성해야합니다.
		BoardVO vo = new BoardVO();
		
			// 디버깅
			log.info("채워넣기 전 : " + vo);
		
		// 데이터 넣어줌 (여기에서는 DB에 들어가는게 아님, 생성된 vo에 들어가는 것)
		vo.setTitle("새로넣는제목5");
		vo.setContent("새로넣는본문5");
		vo.setWriter("새로넣는 글쓴이5");
			
			// 디버깅
			log.info("채워넣은 후 : " + vo);
		
		// vo 내부에 데이터가 바인딩 된 것 확인했으니 메서드 호출 
			// 위에 생성된 vo를 메서드 파라미터에 넣어줌 (xml로 이동해서 DB에 적재됨)
		boardMapper.insert(vo);
	
	}
	
	
	//@Test // 한개의 글 가져오기
	public void getBoard() {
		
		log.info(boardMapper.getBoard(4));
	}
	
	
	 //@Test // 한개의 글 삭제하기
	public void testDeleteBoard() {
		
		boardMapper.deleteBoard(23);
	}
	
	
	//@Test  // 한 개의 글 수정하기(타이틀, 글번호 받음)
	public void testUpdateBoard() {
		boardMapper.updateBoard("글제목수정", 4);
	}
	
	//@Test // 한 개의 글 수정하기 (BoardVO 받음)
	public void testUpdateBoard2() {
		BoardVO vo = new BoardVO();
		
			// 디버깅
			log.info("전달 데이터가 아직 입력 되기 전 vo : " + vo);
		
		// setter로 전달
		vo.setTitle("vo로바꾼제목");
		vo.setContent("vo로 바꾼 내용");
		vo.setBno(5);
		
			// 디버깅
			log.info("전달 데이터가 입력 된 후의 vo : " + vo);
		
		
		boardMapper.updateBoard2(vo);
	}

	// 구문 생성이 어떻게되는지 관측하기 위한 테스트코드
	@Test
	public void testSearchGetList() {
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword("테스트");
		cri.setSearchType("t");
		
		boardMapper.getList(cri);
	}
	
	
	
	
	
}
