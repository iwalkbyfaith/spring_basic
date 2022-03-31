package com.ict.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	// 이름만 일치하면 알아서 받아옵니다. 자료형을 신경쓸 필요가 없습니다.
	@RequestMapping(value="/getData", method=RequestMethod.POST)
						// /getData?data1=데이터1&data2=데이터2에 해당하는 요소를 받아온다.
	public String getData(String data1, int data2, Model model) {
		System.out.println("data1에 들어온 데이터 -> " + data1);
		System.out.println("data2에 들어온 데이터 -> " + data2);
		System.out.println("data2에 100을 더해 정수(int)임을 증명 -> " + (data2 + 100));
		
		// 03.30 추가) .jsp로 바인딩해서 el로 표출하기
		
		int result = data2 + 100;
		
		model.addAttribute("data1", data1);
		model.addAttribute("data2", data2);
		model.addAttribute("result", result);
		
		
		return "getResult";
	}
	
	
	
	// (● 실습1) 외부에서 전송하는 데이터를 /getMoney 주소로 받아오겠습니다.
	// 이 주소는 int won 이라는 형식으로 금액을 받아서
	// 환율에 따른 환전 금액을 콘솔에 찍어줍니다. 환전 화폐는 임의로 정해주세요
	// 결과 페이지는 exchange.jsp로 하겠습니다.
	// 메서드명은 임의로 만들어주세요
	
	// ■ 바인딩 (03.30 추가)
	// 포워딩시 바인딩을 하고 싶다면 Model을 선언합니다 (import org.springframework.ui.Model)
	// 파라미터로 Model model을 추가해줌
	// 다음 model.addAttribute("보낼이름", 보낼자료);
	// 넘어간 데이터는 .jsp 파일에서 el을 이용해 출력합니다.
	// ex -> model.addAttribute("test", 자료);로 바인딩 한 경우 ${test} .jsp에서 출력 가능
	
	// ■ 포스트 방식으로만 받도록 처리하기 (03.30 추가)
	
	@RequestMapping(value="/getMoney", method=RequestMethod.POST)
	public String exchangeMoney(int won, Model model){
		System.out.println("입력된 KRW -> " + won);
		System.out.println("KRW " + won +"원을 USD로 바꾸면 -> " + ( won / 1220.0 ) + " 달러 입니다.");
		model.addAttribute("won", won);
		model.addAttribute("result", won / 1220.0);
		// won을 원본 그대로 보내지 않고 won/1220.0을 보낼 자료 부분에 넣어도 됨.
		// 혹은 double result = (won / 1220.0) 이런식으로 받아서 보낼 자료 부분에 result 변수를 넣어도 됨
		return "exchange";
	}
	
	
	// ● 실습 (03.30)
	// form 페이지와 결과 페이지를 분리해야합니다.
	// 다만 목적지 주소가 .jsp 기준이 아닌, @RequestMapping상의 주소 기준으로 갑니다.
	
	// 주소 moneyForm으로 연결되도록 아래에 어노테이션 + 메서드를 구성해주세요
	// moneyForm.jsp로 연결됩니다.
	// moneyForm.jsp에는 목적지를 #으로 하고 name=won인 폼을 추가로 만들어주세요
	
	@RequestMapping(value="/moneyForm")
	public String goGetMoney() {
		System.out.println("goGetMoney()으로 진입");
		System.out.println("moneyForm.jsp 목적지 : /getMoney");
		
		return "moneyForm";
	}
	
	
	
	// ● 실습2
	// 상단 /getData 주소를 타겟으로 하는 /dataForm을 만들어주세요
	// data1, data2를 자료형에 맞게 폼으로 입력 받아 전송 버튼을 누르면
	// 해당 데이터가 결과 페이지에 나올 수 있도록 .jsp 파일부터 시작해서
	// form 태그나 세부 로직까지 완성시켜주세요.
	
	@RequestMapping(value="/dataForm")
	public String goGetData() {
		System.out.println("goGetData() 진입");
		System.out.println("dataForm.jsp의 목적지 : /getData");
		
		return "dataForm";
	}
	
	
	
	
	// ● 실습3
	// /getData 역시 post만 처리할 수 있게 해주시고
	// 연동된 form 역시 post로 전송하도록 해주세요.
	
	
	
	
	
	
	// Q) 만약 위와 같은 method=RequestMethod.POST <-- 이런 방식을 쓰기 싫다면?
	// A) GetMapping 혹은 PostMapping을 사용.
	
	// 스프링 5버전부터 허용
	// ■ GetMapping = get만 서용하는 매핑 (RequestMethod를 작성할 필요가 없어지는 것)
	@GetMapping(value="/onlyGet")
	public String onlyGet() {
		return "onlyGet";
	}
	
	// ■ PostMapping = post만 서용하는 매핑 (RequestMethod를 작성할 필요가 없어지는 것)
	@PostMapping(value="/onlyPost")
	public String onlyPost() {
		return "onlyPost";
	}
	
	
	
	
	// ● 실습4
	
	// 성적을 입력하는 양식과, 입력하고 제출버튼을 누르면 총점 및 평균을 보여주는 페이지를 제작해보겠습니다.
	// 조건 : 모든 페이지의 접속 주소는 /score 입니다.
	// 성적 입력 폼 같은 경우 get방식 접근만 허용
	// 성적 결과 페이지는 post 방식 접근만 허용합니다.
	//    ==> 같은 주소를 두 개 이상의 페이지에다 걸 수 있음
	
	// 폼에서는 수학, 영어, 언어, 사탐, 컴퓨터 과목의 성적을 각각 입력 받도록 5개의 입력란이 있으며
	// name 속성은 알아서 지정해주세요
	
	// 목적지 페이지에서는 
	// 1) 해당 5개 과목의 성적을 받아서 total 변수에 총점을 저장한 다음 바인딩
	// 2) avg 변수에 평균을 double로 저장한 다음 바인딩
	// 3) 전달 받은 5개 과목도 전부 바인딩해서
	
	// 결과 페이지에 과목별 성적, 총점, 평균 점수를 띄워줍니다.
	
	
	@GetMapping(value="/score")
	public String scoreForm() {
		
		return "scoreForm"; 	
	}
	
	
	@PostMapping(value="/score")
	public String scoreResult(int math, int eng, int kor, int soc, int com, Model model) {
		
		int total = math + eng + kor + soc + com;
		double avg = (math + eng + kor + soc + com) / 5.0;
		
		model.addAttribute("math", math);
		model.addAttribute("eng", eng);
		model.addAttribute("kor", kor);
		model.addAttribute("soc", soc);
		model.addAttribute("com", com);
		
		model.addAttribute("total", total);
		model.addAttribute("avg", avg);
			
		return "scoreResult";
	}
	
	
	
	
	
	
	
	
	
	
	
}
