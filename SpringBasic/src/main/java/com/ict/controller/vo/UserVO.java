package com.ict.controller.vo;

import lombok.Data;

// 가상의 "회원 관리용 VO" (테이블은 없지만 있는 것처럼 가정)
	// 롬복이 깔려있다면 @Data 만 써서 처리 가능
	// lombok의 는 해당 VO의 setter, getter, 생성자, toString을 자동 생성해줍니다.
	// 단 롬복을 사용하기 위해서는 (자세한 것은 03.31 필기)
		// 1. lombok 설치
		// 2. pom.xml에 롬복 관련 세팅

@Data
public class UserVO {

	// 테이블 컬럼 5개에 대응하는 VO
	private int userNum;
	private String userId;
	private String userPw;
	private String userName;
	private int userAge;

	
	
	

}
