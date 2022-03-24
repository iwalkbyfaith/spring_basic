/* 의존성 주입

	클래스 : 가수
	가수는 무대가 있건 없건 노래를 할 수 있기 때문에(물론 있으면 좋지만) 다른 어떤 요소 없이 오직 노래 기능만 넣어줍니다.

 */


package com.ict.controller.di.classfile;

public class Singer {
	
	public void sing() {
		System.out.println("가수가 노래를 합니다.");
	}

}
