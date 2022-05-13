package com.ict.service;

import com.ict.domain.MemberVO;

public interface SecurityService {
	
	// ■ 회원가입 정보 DB에 적재
	public void insertMember(MemberVO vo);
}
