package com.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 빈 컨테이너에 등록해주세요
@Component
public class Library {

	// 라이브러리가 북에 의존하는 상태로 만들어주세요
	// 단 생성자는 void 생성자(아무것도 입력받지 않고 아무것도 안 하는)로 만들어주시고
	
	// 단 멤버 변수 book에 대한 setter만 만들어주세요(public void setBook(Book book))

	private Book book;
	
	public Library() {
		
	}

	// setter 주입을 위해 세터 위에 Autowired를 붙임.
	// ★★★ @Autowired는 1. 멤버변수 위, 2. 생성자 위(생성자주입일시), 3. setter(세터주입일시) 위에 붙일 수 있습니다. ★★★
	
	//@Autowired
	public void setBook(Book book) {
		this.book = book;
	}
	

	// 멤버 변수 book을 이용해 "도서관에서 책을 읽습니다"라는 문장을 실행하는
	// browse 메서드를 생성해주세요
	public void browse() {
		System.out.print("도서관에서 ");
		book.read();
	}
	
	
	
}
