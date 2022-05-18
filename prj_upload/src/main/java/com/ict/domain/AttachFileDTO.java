package com.ict.domain;

import lombok.Data;

@Data
public class AttachFileDTO {
	
	private String fileName;		// 파일 이름
	private String uploadPath;		// 몇 월 며칠 경로에?
	private String uuid;			// uuid (랜덤 발급) : 프라이머리키의 역할을 하는 것 (같은게 나올 확률이 낮으니까)
	private boolean image;			// 이미지인지 아닌지의 여부 : 일반 파일이면 썸네일을 만들면 안되니까

}
