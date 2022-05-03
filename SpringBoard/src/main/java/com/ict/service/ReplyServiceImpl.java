package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.ReplyVO;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements IReplyService {

	// ■ 서비스가 매퍼를 호출하므로 매퍼를 선언해서 사용.
	@Autowired
	private ReplyMapper mapper;
	
	
	// ■ 05.02 댓글 추가시 board_tbl에도 영향을 주기 때문에, board 테이블을 수정하는 Mapper를 추가 선언해야함.
	@Autowired
	private BoardMapper boardMapper;
	
	
	
	// ■ 특정 게시판의 전체 댓글 가져오기
	@Override
	public List<ReplyVO> listReply(Long bno) {
		return mapper.getList(bno);
	}
	
	// ■ 댓글 작성
	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
	}

	// ■ 댓글 수정
	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
		
	}
	
	// ■ 댓글 삭제
	@Transactional
	@Override
	public void removeReply(Long rno) {
		// 글 삭제 전에 먼저 bno번을 채취해 놓고
		Long bno = mapper.getBno(rno);
		// 다음 글 삭제해야 문제 없이 글 번호를 가져옵니다.
		mapper.delete(rno);
		// ■ 05.02 추가
		// 해당 댓글의 글 번호 가져오기
		// 그 글 번호의 Replycount를 -1 하기
		boardMapper.updateReplyCount(bno, -1);
	}
	
	
	
	

}
