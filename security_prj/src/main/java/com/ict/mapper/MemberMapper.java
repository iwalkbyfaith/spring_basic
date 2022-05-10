package com.ict.mapper;

import com.ict.domain.MemberVO;

public interface MemberMapper {

		// ■ 데이터 가져오기
		public MemberVO read(String userId);
}
