package com.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ict.domain.BoardVO;

public interface BoardMapper {

	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을 어노테이션을 이용해주세요 ( 가능성: 0개~3개 )
	// @Select ("SELECT * FROM board_tbl WHERE bno < 4")
	
	// 인터페이스에서는 
		// 1. 리턴값(리턴 되는지 아닌지, 어떤 자료형이 리턴되는지)
		// 2. 메서드 이름이 뭔지 생각하면 된다 (추상 메서드 배치)
	
	
	// ■ Select ("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList();
	
	
	// ■ insert
	// insert 구문 실행용으로 메서드를 선언합니다.
	// VO 내부에 적혀있는 정보를 이용해 insert를 합니다. (String title, Strimng content... 이렇게 받아도 됨)
	// 파라미터값이 여러개인 경우는 vo를 쓰고, 1개/2개 밖에 안되면 그냥 각각 쓰는게 나을 듯 하다.
	// BoardVO를 매개로 insert 정보를 전달받음.
	public void insert(BoardVO vo);
	
	
	// ■ SELECT (특정 글번호 long bno을 입력 받아 콘솔에 찍어주는 메서드)
	public BoardVO getBoard(long bno);
	
	
	// ■ DELETE ( 특정 글 번호를 하나 입력 받아 글을 삭제하는 메서드 )
	public void deleteBoard(long bno);
	
	
	// ■ UPDATE (혼자 실습, 해당 글 번호 게시글 수정 - title, bno 받음)
	public void updateBoard(@Param("title")String title, @Param("bno")long bno);
	
	// ■ UPDATE2 (혼자 실습, 해당 글 번호 게시글 수정 - vo로 받음)
	public void updateBoard2(BoardVO vo);
	
	
	
}
