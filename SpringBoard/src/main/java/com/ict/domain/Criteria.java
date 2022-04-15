package com.ict.domain;

import lombok.Data;

@Data
public class Criteria {

	
	private int pageNum = 1;       // 페이지 번호 (페이지가 21억개 될 수는 없으니까? int로) ★ 디폴트값을 위해 =1을 넣어줌.
	private int number = 10;   	   // 글 개수
	
	
	// 페이지 * 페이지당 숫자가 실제 limit 구문에 들어갈 시작점이 된다.
	// mybatis는 getter를 가져다 쓸 수 있다.
	
	
	// ● 시작 페이지
	public int getPageStart() {
		return (this.pageNum -1) * number;
	}
		// 오라클이어서 위의 메서드 안 만들고 getter로 처리할 수도 있을 것 같다고 하심

	
	// ● 끝 페이지
	public int getPageEnd() {
		return this.pageNum * number;
	}
	
	
	
}
