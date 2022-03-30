package com.ict.controller.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.controller.di.classfile.BalladSinger;
import com.ict.controller.di.classfile.Book;
import com.ict.controller.di.classfile.Broadcast;
import com.ict.controller.di.classfile.Library;
import com.ict.controller.di.classfile.PopSinger;
import com.ict.controller.di.classfile.Singer;
import com.ict.controller.di.classfile.Stage;

public class DiMainSpringVer {

	public static void main(String[] args) {
		
		// root-context라는 공장에 저장된 객체를 뽑아서 써야합니다.
		// 1. 가져오기 위한 호출 코드를 작성해보겠습니다.
		
//		// 호출코드
//		GenericXmlApplicationContext context = 
//				new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
		
		// ■ 03.29 추가) xml 쪼개기
		// 방법1 경로 연달아 집어넣기
//		GenericXmlApplicationContext context = 
//				new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml", 
//												 "file:src/main/webapp/WEB-INF/spring/root-context2.xml");
		
		// 방법2 문자 배열로 만들어서 ??? 이거 다시 보기
//		String[] address = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
//				 "file:src/main/webapp/WEB-INF/spring/root-context2.xml"};
//			
//		GenericXmlApplicationContext context = new GenericXmlApplicationContext(address);
		
		
//		GenericXmlApplicationContext context = 
//		new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
		
		
		// 방법3 와일드카드 별 활용하기 (root-로 시작하는 모든 애들 들어가기 가능) -> 보통 많이씀
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-*.xml");
//		
//		// 2. 공장 내부 객체 가져오기
//		// 문법 : context.getBean("공장 내부 명칭", 클래스파일명.class(설계도));
//		Singer singer = context.getBean("singer", Singer.class);
//		
//		// 3. 가져온 객체 사용하기 ( => new 키워드로 만드는 것과 차이가 없음을 볼 수 있다)
//		singer.sing();
//		
//		// 4. 여러분들이 직접 Stage를 가져다가 실행시키는 코드를 작성해주세요
//		Stage stage = context.getBean("stage", Stage.class);
//		stage.perform();
		
		// 실행 잘됨 근데
		// 해당 코드를 pom.xml에서 4.1.5 버전으로 다운 그레이드하면 바로 에러가 난다.
		// No default constructor found
		// 스테이지에는 싱어가 필요한데 싱어를 넣어주지 않아서 그런 것.
		// 시스템에게는 싱어와 스테이지를 따로따로 공장에 넣어놓은 것이라고 생각(싱어를 스테이지에 넣어줘야하는 것을 우리가 직접 알려줘야 했었음)
		// 그때 쓰는 방식이 Stage 파일에 @Autowired라고 적어주는 것
		
		// 이제 싱어를 만들지 않아도 스테이지를 바로 만들어서 쓸 수 있는 것. (주석처리해도 스테이지 코드 가능가능)
		// 스테이지를 만드는데 필요했던 싱어가 '빈 컨테이너' 안에 있기 때문에.
		
		
		// 5. 실습
		//1. Broadcast 클래스를 만들어주시고 Stage를 입력 받아야 생성 가능하세 해주세요.
		//2. Broadcast의 송출 기능을 담당하는 onAir 메서드는 "방송 송출중인 무대에서 가수가 노래를 합니다"라는 문장이 나오도록 세팅해주세요
		//3. DiMainSpringver에서 Stage와 Singer를 건너뛰고 바로 Broadcast를 만들어서 실행할 수 있도록 세팅을 마친 다음 실제로 실행까지 해주세요
		
//		Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
//		broadcast.onAir();
			// 발라드/팝 싱어를 만들고 나면 에러가 뜸 (그냥 싱어는 @Component 주석처리한 상태)
			// available: expected single matching bean but found 2: balladSinger,popSinger 
			// (해결) @Qualifier
		
//		
		// 6. 발라드/팝 싱어
//		BalladSinger bs1 = context.getBean("balladSinger", BalladSinger.class);
//		bs1.sing();
//		
//		PopSinger ps1 = context.getBean("popSinger", PopSinger.class);
//		ps1.sing();
		
//		
//		// 7. 수동생성 bean인 stage1, stage2 가져와서 사용하기
//		// context.getBean("빈 컨테이너 안에 있는 이름", 설계도로 쓸 파일.class)
//		System.out.println("-----수동으로 만든 스테이지--------");
//		
//		// 수동으로 만든 stage1
//		Stage stage1 = context.getBean("stage1", Stage.class);
//		stage1.perform();
//		
//		// 수동으로 만든 stage2
//		Stage stage2 = context.getBean("stage2", Stage.class);
//		stage2.perform();
//		

		// 03.29 추가
		// ■ setter 주입 <= 을 사용해보기 위한 코드 (나중에 library1을 만들고 library 대신 1을 넣어봄)
		
		Library library = context.getBean("library1", Library.class);
		library.browse();
		
		
		
		
	}

}
