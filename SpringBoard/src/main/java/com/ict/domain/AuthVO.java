package com.ict.domain;

import lombok.Data;

@Data
public class AuthVO {

	private String userId;	// 유저 아이디
	private String auth;	// 유저 권한
}
