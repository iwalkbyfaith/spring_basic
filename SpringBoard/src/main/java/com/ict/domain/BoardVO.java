package com.ict.domain;

import java.sql.Date;

import lombok.Data;


// lombok을 이용해 get-setter, 생성자, toString을 만들어주세요.

@Data
public class BoardVO {
	
	private long bno;       // 게시글 수가 21억 이상일 것 같으면 long
	private String title;
	private String content;
	private String writer;
	private Date regdate;     // Date 타입이 대신 Timestamp를 쓰면 훨씬 더 정교한 시간을 표현 가능
	private Date updatedate;

}
