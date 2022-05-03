package com.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;

public interface BoardMapper {

	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을 어노테이션을 이용해주세요 ( 가능성: 0개~3개 )
	// @Select ("SELECT * FROM board_tbl WHERE bno < 4")
	
	// 인터페이스에서는 
		// 1. 리턴값(리턴 되는지 아닌지, 어떤 자료형이 리턴되는지)
		// 2. 메서드 이름이 뭔지 생각하면 된다 (추상 메서드 배치)
	
	
	// ■ SELECT 
	//public List<BoardVO> getList(long pageNum);
	// 04.12 변경
		// 버튼 추가를 위해 PageNum 대신 Criteria를 활용합니다.
	//public List<BoardVO> getList(Criteria cri);
	public List<BoardVO> getList(SearchCriteria cri);

	
//	@Select("SELECT now()")
//	public String getTime();
	
	
	// ■ INSERT
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
		// 두 개 이상의 파라미터를 따로따로 전달할때는 각 파라미터 왼쪽에 @Param을 붙여줍니다.
	public void updateBoard(@Param("title")String title, @Param("bno")long bno);
	
	// ■ UPDATE2 (해당 글 번호 게시글 수정 - vo로 받음) (전달 변수가 ★2개 이상★이면 vo를 쓰기! -> 권장)
	public void updateBoard2(BoardVO vo);
	
	
	// 04.12 / 04.14 파라미터 받도록 수정
	// ■ 전체 글 개수 받아오기
	//public int countPageNum();
	public int countPageNum(SearchCriteria cri);
	
	
	// 05.02
	// ■ 댓글을 쓰면 해당 글의 replycount를 증가시켜주는 메서드 (글번호와 증감량(+/-)을 받음)
	public void updateReplyCount(@Param("bno") Long bno, @Param("amount") int amount);
	
	
	
	
}
