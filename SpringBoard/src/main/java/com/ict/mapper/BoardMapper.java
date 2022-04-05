package com.ict.mapper;

import java.util.List;

import com.ict.vo.BoardVO;

public interface BoardMapper {

	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을 어노테이션을 이용해주세요 ( 가능성: 0개~3개 )
	// @Select ("SELECT * FROM board_tbl WHERE bno < 4")
	
	// 인터페이스에서는 
		// 1. 리턴값(리턴 되는지 아닌지, 어떤 자료형이 리턴되는지)
		// 2. 메서드 이름이 뭔지 생각하면 된다
	
	
	// ■ Select ("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList();
	
	
	// ■ insert
	// insert 구문 실행용으로 메서드를 선언합니다.
	// VO 내부에 적혀있는 정보를 이용해 insert를 합니다.
}
