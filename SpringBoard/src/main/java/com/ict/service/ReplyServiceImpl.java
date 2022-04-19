package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.domain.ReplyVO;
import com.ict.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements IReplyService {

	// ■ 서비스가 매퍼를 호출하므로 매퍼를 선언해서 사용.
	@Autowired
	private ReplyMapper mapper;
	
	
	
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
	@Override
	public void removeReply(Long rno) {
		mapper.delete(rno);
		
	}

}
