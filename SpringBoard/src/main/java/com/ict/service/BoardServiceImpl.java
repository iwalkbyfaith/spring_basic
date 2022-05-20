package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.BoardAttachVO;
import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardAttachMapper;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

// 1. IBoardService 인터페이스 구현 ( implements 사용 )
// 2. bean 컨테이너에 등록 ( @Service 어노테이션 등록)

@Log4j
@Service
public class BoardServiceImpl implements IBoardService{
	
	// 3. 서비스가 DAO(Mapper.java)를 호출한다면 선언을 하고 의존성 주입을 해야한다.
	
	@Autowired
	private BoardMapper boardMapper;
	
	// 05.03 글 삭제시 댓글까지 삭제되게 해야 하므로
	@Autowired
	private ReplyMapper replyMapper;
	
	// 05.20 보드 적재시 첨부파일도 적재할 수 있도록
	@Autowired
	private BoardAttachMapper attachMapper;

	
	// ■ 리턴 자료형이 없는 insert, delete, update 구문은 사용자 행동 기준으로 메서드를 나눕니다.
		// 리턴 자료형이 void라면 하나의 메서드 안에 때려 넣어도 되는 것
	
	// ■ 리턴 자료형이 있는 select 구문은 하나의 메서드가 하나의 쿼리문을 담당한다.
	
	
	// ● 모든 게시글 가져오기(페이징처리 됨)
//	@Override
//	public List<BoardVO> getList(Criteria cri) {
//		
//		// 원래 컨트롤러에서 호출하던 getList를 서비스로 옮긴 것
//		return boardMapper.getList(cri);
//	}
	
	@Override
	public List<BoardVO> getList(SearchCriteria cri) {
		
		// 원래 컨트롤러에서 호출하던 getList를 서비스로 옮긴 것
		return boardMapper.getList(cri);
	}

	
	// ● 전체 페이지 가져오기? (04.14 밑의 코드로 수정)
//	@Override
//	public int countPageNum() {
//		return boardMapper.countPageNum();
//	}
	
	@Override
	public int countPageNum(SearchCriteria cri) {
		return boardMapper.countPageNum(cri);
	}


	// ● 보드 디테일 보기
	@Override
	public BoardVO getBoard(long bno) {
		
		return boardMapper.getBoard(bno);
	}


	// ● 게시판 적재하기
	@Transactional
	@Override
	public void insert(BoardVO vo) {
		boardMapper.insert(vo);
		
		// * 05.20 첨부파일에서 ↑ 방금 적재된 vo의 bno가 필요함
		//		   하지만 실제로는 오라클에서 nextval을 사용하고, 들어온 vo는 null값이기 때문에 기본적으로 0으로 초기화됨
		//		   그래서 boardMapper쪽 쿼리문을 고쳐줘야한다. (id="insert")
		log.info("게시판 적재시 getBno하면 바로 들어오는지 test -> " + vo.getBno());
		
		// * 05.20 게시판 적재시, 같이 넘어온 첨부 파일도 테이블에 적재되도록
		
			// 1) 들어온 첨부파일이 없으면 if문에서 탈출(!?)
			if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
				return;
			}
			
			// 2) 첨부파일이 있다면, attach 테이블에 넣어줌
			vo.getAttachList().forEach(attach -> {
				
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
				
			});// end forEach
	}

	// ● 게시판 삭제하기(05.03 개선, 댓글까지 같이 삭제되게 하기)
	@Transactional
	@Override
	public void deleteBoard(long bno) {
		// 1) 댓글을 먼저 삭제
		replyMapper.deleteAllRepl(bno);
		// 2) 그 다음 글 삭제
		boardMapper.deleteBoard(bno);
	}

	
	// ● 게시판 수정하기
	@Override
	public void updateBoard2(BoardVO vo) {
		boardMapper.updateBoard2(vo);
	}


	// ■ 05.20 DB에서 게시물에 연동된 첨부파일 목록 가져오기
	@Override
	public List<BoardAttachVO> getAttachList(long bno) {
		log.info("get Attach list의 bno -> " + bno);
		return attachMapper.findByBno(bno);
	}
	
	
	
	
	

	
	

	
}
