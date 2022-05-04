package com.ict.service;

import java.util.List;

import com.ict.domain.ReplyVO;

public interface IReplyService {
	
	// ■ 특정 게시판의 전체 댓글 가져오기
	public List<ReplyVO> listReply(Long bno);
	
	// ■ 댓글 작성
	public void addReply(ReplyVO vo);
	
	// ■ 댓글 수정
	public void modifyReply(ReplyVO vo);
	
	// ■ 댓글 삭제
	public void removeReply(Long rno);
	

	
	

}
