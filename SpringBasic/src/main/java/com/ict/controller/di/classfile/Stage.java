/* 의존성 주입

	클래스 : 무대
	무대는 가수가 있어야 성립하며, 그때그때 다른 가수를 세울 수도 있습니다.

 */

package com.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Stage {
	
	// 가수 멤버 변수
	// @Autowired를 입력시 해당 자료형과 일치하는 부품이 공장 내에 존재하면 자동으로 결합해줍니다. (변수나 생성자 위 둘 중 하나에 붙이면 됨)
		// 5버전 부터는 멤버변수가 '한 개'만 있는 경우는 자기가 알아서 자동으로 넣어준다. ( 두 개 이상이면 자동으로 안됨 )
		// 하지만 붙여 넣고 쓰는 버릇을 들이는게 좋을수도? (선생님도 이걸 권장하심)
		// @Autowired는 @Inject로 대체할 수 있습니다.
	//@Autowired
	//@Qualifier("popSinger") // ★ 팝vs발라드 -> (우선권)팝, @Qualifier 쓸 때는 밑에 빈 생성자 꼭 만들기
	// root-context에서 stage1,2를 만들어서 지정해주었으므로 퀄리파이어를 주석처리
	private Singer singer;
	
	
	// 4버전까지 @Autowired를 쓰려면 아무 작업도 하지 않는 디폴트 생성자가 추가되어야 합니다. (입력 받는 생성자, 입력 안 받는 생성자가 있어야 하는 것)
	// 우리는 5버전 사용하니까 이거는 필요없는 것.
	// 그런데 !!!!! 5번에서라도 @Qualifier 어노테이션 사용시는 디폴트 생성자가 필요하다.
	public Stage() {}
		
	
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
