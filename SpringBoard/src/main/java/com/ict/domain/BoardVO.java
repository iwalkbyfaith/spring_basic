package com.ict.domain;

import java.sql.Date;

import lombok.Data;


// lombok을 이용해 get-setter, 생성자, toString을 만들어주세요.

@Data
public class BoardVO {
	
	// private int rn (테스트코드 작성할때 거슬리면 추가하면 됨. 안 작성하면 unread라고 나옴)
	private long bno;       // 게시글 수가 21억 이상일 것 같으면 long
	private String title;
	private String content;
	private String writer;
	private Date regdate;     // Date 타입이 대신 Timestamp를 쓰면 훨씬 더 정교한 시간을 표현 가능
	private Date updatedate;

	// 05.02 해당 글의 댓글이 몇 개인지 카운트하는 변수 추가
	private int replycount;
}
