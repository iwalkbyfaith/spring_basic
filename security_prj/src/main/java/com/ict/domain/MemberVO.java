package com.ict.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

		private String userId;			// 아이디
		private String userPw;			// 비밀번호
		private String userName;		// 이름
		private boolean enabled;		// 인증
		
		private Date regDate;			// 가입일
		private Date updateDate;		// 권한 변경일?
		
		private List<AuthVO> authList;	// 권한 목록 (1:1 대응이 아니라는 것 주의)
											// ★ VO를 리스트로 받아오는 경우에는 xml에서 컬렉션으로 처리해주어야 함.
											// 일반 result로 받으면 1:1 매칭이 되기 때문에 컬렉션을 사용하는 것
											// 더 자세한 설명 : 마이바티스 공식 설명(마이바티스.org > mapper xml > result maps > 검삭 : has many)
}
