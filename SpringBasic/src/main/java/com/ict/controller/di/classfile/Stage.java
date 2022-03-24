/* 의존성 주입

	클래스 : 무대
	무대는 가수가 있어야 성립하며, 그때그때 다른 가수를 세울 수도 있습니다.

 */

package com.ict.controller.di.classfile;

public class Stage {
	
	// 가수 멤버 변수
	private Singer singer; 
	
	
	// 생성자
	// 무대에 설 가수를 입력해야 생성자가 호출되도록 처리
	public Stage(Singer singer) {
		this.singer = singer;
	}
	
	
	public void perform() {
		System.out.print("무대에서 ");
		this.singer.sing();
	}

}
