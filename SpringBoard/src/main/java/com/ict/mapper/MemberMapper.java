package com.ict.mapper;

import com.ict.domain.MemberVO;

public interface MemberMapper {

		// ■ 데이터 가져오기
		public MemberVO read(String userId);
		
		// ■ 회원가입 로직 - 회원정보 기입
		public void insertMemberTbl(MemberVO vo);
		
		// ■ 회원가입 로직 - 권한 목록 기입 ( ★ 다중 insert 구문은 update 태그로 처리한다 )
		public void insertMemberAuth(MemberVO vo); // MemberVO 내부에 authList가 있기 때문에 MemberVO로 받는다.
}
