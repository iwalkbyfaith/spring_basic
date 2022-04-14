package com.ict.service;

import java.util.List;


import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;

// 구현 클래스 BoardServiceImpl의 뼈대가 됨
// root-context.xml에서 컴포넌트 스캔까지 완료해야 등록됨
public interface IBoardService {
	
	// 인터페이스 내에 먼저 메서드를 선언하고, impl 클래스에서 구현한다.
	// Mapper.java의 선언부를 가져오면 끝!
	
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	// ※ 중요중요 : 실제로 주입되는 것은 BoardServiceImpl.java이기 때문에 여기(IBoardService)를 거쳐서 가는게 아니라 컨트롤러에서 바로 임플로 간다 !!!!
		// 실제로도 빈 컨테이너의 빈즈 그래프를 확인해보면 'BoardServiceImpl'은 있지만 'IBoardService'는 없음
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	// ■ 게시판 리스트 보기
	//public List<BoardVO> getList(Criteria cri);
	public List<BoardVO> getList(SearchCriteria cri);
	
	// ■ 전체 글 개수 받아오기 (04.14 수정)
	//public int countPageNum();
	public int countPageNum(SearchCriteria cri);
	
	// ■ 디테일 페이지 보기
	public BoardVO getBoard(long bno);
	
	// ■ 데이터 적재하기
	public void insert(BoardVO vo);
	
	// ■ 게시판 삭제하기
	public void deleteBoard(long bno);
	
	// ■ 게시판 수정하기
	public void updateBoard2(BoardVO vo);

}
