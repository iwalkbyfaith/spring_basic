package com.ict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.MemberVO;
import com.ict.mapper.MemberMapper;

@Service
public class SecurityServiceImpl implements SecurityService{

	@Autowired
	private MemberMapper mapper;
	
	// ■ 회원가입 정보 DB에 적재
	@Transactional
	@Override
	public void insertMember(MemberVO vo) {
		
		// ● 회원가입 로직 - 회원정보 기입
		mapper.insertMemberTbl(vo);
		// ● 회원가입 로직 - 권한 목록 기입
		mapper.insertMemberAuth(vo);
	}

}
