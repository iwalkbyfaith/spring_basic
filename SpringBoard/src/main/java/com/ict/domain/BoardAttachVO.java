package com.ict.domain;

import lombok.Data;

@Data
public class BoardAttachVO {
	
	// 그림파일을 업로드하는데 필요한 변수
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;	// 다른 프로젝트에서 image
	
	// 몇 번 글에 첨부파일이냐를 따져줌
	private long bno;

}
