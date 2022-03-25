/* 의존성 주입

	- 스테이지를 만드려면 무조건 싱어가 있어야한다. (스테이지 생성자에 싱어타입을 넣어주어야 하므로. 생성도 안되고 메서드 실행도 안됨)
	- Singer와 Stage는 패키지가 다르기때문에 import를 시켜주어야 한다.
	- (결론) 무대는 싱어에 의존한다
	- 클래스가 다른 클래스를 멤버 변수로 가질 수 있고, 메서드 실행에도 다른 클래스가 필요한 경우가 있음. => 의존 상태
	- 의존 = 특정한 요소가 다른 요소를 필요로 하는 것.

 */

package com.ict.controller.di;

import com.ict.controller.di.classfile.Singer;
import com.ict.controller.di.classfile.Stage;

public class DiMainJavaVer {

	public static void main(String[] args) {

		// 가수, 무대를 생성한 다음
		Singer singer = new Singer();
		Stage stage = new Stage(singer);
		
		// 무대의 공연(perform)메서드를 호출해주세요.
		stage.perform();
		
		// 그냥 가수가 노래하는 것도 가능한지 테스트해주세요
		singer.sing();

	}

}