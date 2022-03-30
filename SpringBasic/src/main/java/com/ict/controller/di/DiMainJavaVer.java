/* 의존성 주입

	- 스테이지를 만드려면 무조건 싱어가 있어야한다. (스테이지 생성자에 싱어타입을 넣어주어야 하므로. 생성도 안되고 메서드 실행도 안됨)
	- Singer와 Stage는 패키지가 다르기때문에 import를 시켜주어야 한다.
	- (결론) 무대는 싱어에 의존한다
	- 클래스가 다른 클래스를 멤버 변수로 가질 수 있고, 메서드 실행에도 다른 클래스가 필요한 경우가 있음. => 의존 상태
	- 의존 = 특정한 요소가 다른 요소를 필요로 하는 것.

 */

package com.ict.controller.di;

import org.springframework.context.support.GenericXmlApplicationContext;
import com.ict.controller.di.classfile.Book;
import com.ict.controller.di.classfile.Library;
import com.ict.controller.di.classfile.Singer;
import com.ict.controller.di.classfile.Stage;

public class DiMainJavaVer {

	public static void main(String[] args) {

//		// 가수, 무대를 생성한 다음
//		Singer singer = new Singer();
//		Stage stage = new Stage(singer);
//		
//		// 무대의 공연(perform)메서드를 호출해주세요.
//		stage.perform();
//		
//		// 그냥 가수가 노래하는 것도 가능한지 테스트해주세요
//		singer.sing();

		
		// 03.29 setter 주입
		// 기존 자바에서는 Book, Library를 둘 다 생성해야 실행 가능
		// 생성자 주입이 가능할때는 생성하면서 book을 Library에 넣으면 됐었음
			// Library library = new Library(book); 이렇게
		Book book = new Book();
		Library library = new Library();  // 아무 동작도 하지 않는 라이브러리의 생성자 (아무것도 없으니까)
		library.setBook(book); // 멤버 변수 book 채우기
		library.browse();
		
		// 결론 -> 주입을 안해주면 실행이 안된다

	}

}
