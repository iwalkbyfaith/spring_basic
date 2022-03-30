package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 어노테이션에 네 종류가 있었는데 (@Component, @Repository, @Controller, @Service)
// 컨트롤러를 만드는 경우이니 당연히 @Controller를 씁니다.
// 이렇게하면 appServlet 밑의 servlet-context.xml에 등록이 된다. (Beans Graph를 확인)

@Controller
public class BasicController {
	
	// RequestMapping의 value는 localhost:8181/어떤주소 로 접속시 해당 로직이 실행될 지 결정합니다.
	// 아무것도 안 적으면 기본적으로 get방식을 허용합니다.
	
	@RequestMapping(value="/goA")
	// 아래에 해당 주소로 접속시 실행하고싶은 메서드를 작성합니다.
	
	public String goA() {
		System.out.println("goA 접속이 감지되었습니다.");
		// return "goA";라고 적으면 views폴더 내부의 goA.jsp 파일을 보여줍니다. (views라는 폴더와 연동이 되어있음)
		// 기본으로 포워딩을 쓰도록 되어있는 것 (리다이렉트도 가능하긴 함. 이따가 가르쳐주신다고)
		return "goA";
		
	}
	
	// 16번 라인의 주소로 접속을 하면 코드 19-25까지 쭉 실행을 하는 것
	// 실행해보면 404 에러가 뜨고 파일 [/WEB-INF/views/goA.jsp]을(를) 찾을 수 없습니다. 라고 뜸
	// 왜냐면 goA.jsp라는 파일을 만든 적이 없기 때문
	
	// 결론 : 무슨 주소로 접속하면 뭐가 실행되도록 하겠다 <- 라는 내용이 컨트롤러에 들어가는 것.

	// 리퀘스트 매핑부터 시작해서 중괄호 닫는 부분까지 ( } )가 한 몸이라고 생각하면 됨
	
	// (● 실습) /goB로 접속했을때 실행되는 b.jsp창이 열리도록 아래에 세팅해주세요.
	// ※ 밸류값(주소), 메서드 이름, 리턴되는 .jsp 파일의 이름이 무조건 같아야하는 것은 아님!
	// 메서드 이름이 bananaB() 이어도 b.jsp로 가는 것에 상관 없다는 뜻 (심지어 한글 명으로 지어도 상관 없음)
	
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB 접속이 감지되었습니다");
		return "b";
	}
	
	
	// (● 실습2) 여러분들이 성씨를 기준 ("/koh")으로 패턴을 잡고
	// 결과 페이지는 "XXX의 페이지입니다." 라는 문장이 뜨도록 처리
	@RequestMapping(value="/koh")
	public String daeun() {
		System.out.println("/koh 패턴으로 메서드 daeun()에 진입");
		return "daeun";
	}
	
	
	
	// 외부에서 전송하는 데이터는 선언된 변수로 받습니다. 
	// (예전에는 request.getparameter로 받았고 int면 parseInt 해줘야했는데 이제는 아님)
	// 이름만 일치하면 알아서 받아옵니다.
	// 자료형을 신경쓸 필요가 없습니다.
	@RequestMapping(value="/getData")
						// /getData?data1=데이터1&data2=데이터2에 해당하는 요소를 받아온다.
	public String getData(String data1, int data2) {
		System.out.println("data1에 들어온 데이터 -> " + data1);
		System.out.println("data2에 들어온 데이터 -> " + data2);
		System.out.println("data2에 100을 더해 정수(int)임을 증명 -> " + (data2 + 100));
		
		return "getResult";
	}
	
	
	
	// (● 실습1) 외부에서 전송하는 데이터를 /getMoney 주소로 받아오겠습니다.
	// 이 주소는 int won 이라는 형식으로 금액을 받아서
	// 환율에 따른 환전 금액을 콘솔에 찍어줍니다. 환전 화폐는 임의로 정해주세요
	// 결과 페이지는 exchange.jsp로 하겠습니다.
	// 메서드명은 임의로 만들어주세요
	
	@RequestMapping(value="/getMoney")
	public String exchangeMoney(int won) {
		System.out.println("입력된 KRW -> " + won);
		System.out.println("KRW " + won +"원을 USD로 바꾸면 -> " + ( won / 1220.0 ) + " 달러 입니다.");
		return "exchange";
	}
	
	
	
	
}
