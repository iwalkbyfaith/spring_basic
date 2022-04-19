package com.ict.mapper;

import java.util.List;

import com.ict.domain.ReplyVO;

public interface ReplyMapper {
	
	// ■ 특정 게시판 bno번 글의 전체 댓글 목록 가져오기 (게시판 글 번호 받음)
	public List<ReplyVO> getList(Long bno);
	
	// ■ 댓글 작성
	public void create(ReplyVO vo);
	
	// ■ 댓글 수정
	public void update(ReplyVO vo);

	// ■ 댓글 삭제 (댓글 번호 받음)
	public void delete(Long rno);

}
