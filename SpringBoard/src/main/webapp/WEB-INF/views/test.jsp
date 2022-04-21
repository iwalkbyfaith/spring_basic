<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Ajax 테스트</h2>
	
	<!-- ■ ul태그 -->
	<ul id="replies">
	
	</ul>
	
	<!-- ■ ul태그 test -->
	<!-- #test 사이에 daum.net으로 이동하는 a태그를 jquery를 이용해 넣어주세요 -->
	<ul id="test"></ul>
	
	<!-- ■ 버튼 만들어서 연결해보기 -->
	<button id="testBtn">테스트 버튼</button>
	
	
	<!-- jquery는 이곳에 --> <!-- head 태그 사이에 두어도 상관없음 -->
	<!-- 경로 : 사이트 > 다운로드 > google CDN > 3.x snippet: 아래의 코드 복붙 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	
	<%--
	<!-- ■ 비동기 요청 부분1 : 콘솔에만 찍기 --> 
	<script type="text/javascript">
		let bno = 196636;
		
				  //주소(첫번째 파라미터)    // (두번째 파라미터)콜백함수 주소 요청으로 얻어온 json을 어떻게 처리할지
		$.getJSON("/replies/all/" + bno, function(data){
			console.log(data.length);    // 크롬 콘솔 창에 모든 댓글 개수가 출력됨
			//console.log(data);			   // 크롬 콘솔 창에 모든 댓글이 json 형식으로 출력됨
		});
				  
		// $.getJSON의 여는 부분과 닫는 부분 주의
			// 1. $.getJSON("/replies/all/" + bno,   ==> 196636번에 대한 화면으로 요청이 들어감
			// 2. function(data){					 ==> 얻어온 json 데이터를 콜백함수(펑션)에 넣어서
			// 3. console.log(data);				 ==> 콘솔에 찍어라
		
	
	</script>

	
	<!-- ■ 비동기 요청 부분2 : 콘솔에 찍고, 화면에도 출력-->
		<!-- 실습1) 1-1 네이버로 이동하기 str1 -->
	<script type="text/javascript">
		let bno = 196636;
		
				  //주소(첫번째 파라미터)    // (두번째 파라미터)콜백함수 주소 요청으로 얻어온 json을 어떻게 처리할지
		$.getJSON("/replies/all/" + bno, function(data){
			
			// 1. 문자열을 이용해 태그를 생성하거나 끼워 넣을 수 있음.
			//    빈 문자열을 만들어놓고 거기에 댓글 정보를 저장해 화면에 전송
			let str ="";
				// 1.1 태그가 먹힌다는 것을 보기 위해서 네이버로 가는 str1 만들어서 실험
			//let str1 = "<a href='https://www.naver.com'><h1>네이버로 넘어가기</h1></a>"
			console.log(data.length);    // 크롬 콘솔 창에 모든 댓글 개수가 출력됨
			
			$(data).each(
				
					function(){
						str += "<li data-rno='" + this.rno + "' class='replyLi'>"
							+ this.rno + ":" + this.reply
							+ "</li>";						
					});
			// 비어있는 ul 태그에다가 str을 넣어준 것
			$("#replies").html(str);
				// 1.1 의 결과
				// $("#replies").html(str1);
			
		});
				  
		<!-- 버튼(testBtn) 클릭시 발동되는 이벤트 -->
		// testBtn 클릭시 81~89번 라인 실행
		$("#testBtn").on("click", function(){
				
			// 1. #test에 넣어줄 문자를 생성한다.
				let str2 = "<a href='https://www.daum.net/'>다음으로 넘어가기</a>"
				
			// 2. #test를 jquery로 잡고, 태그 내부에 str을 넣어준다.
				$("#test").html(str2);
				
		});
	
	</script>
	
	 --%>
	
		
	<script type="text/javascript">
		let bno = 196636;
		
				  //주소(첫번째 파라미터)    // (두번째 파라미터)콜백함수 주소 요청으로 얻어온 json을 어떻게 처리할지
		$.getJSON("/replies/all/" + bno, function(data){
			
			// 문자열을 이용해 태그를 생성하거나 끼워 넣을 수 있음.
			// 빈 문자열을 만들어놓고 거기에 댓글 정보를 저장해 화면에 전송
			let str ="";
			console.log(data);    // 크롬 콘솔 창에 모든 댓글 개수가 출력됨
			
			// ● 들고 온 데이터를 반복해서 출력하기
			// $(JSON형식 데이터).each => 내부 JSON을 향상된 for문 형식으로 처리합니다.
			// 역시 내부에 콜백함수(로직이 실행되었을떄 추가로 실행할 구문을 정의하기 위해 파라미터로 넣는 함수)
			// 를 이용해 data를 하나하나 향상된 for문 형식으로 처리할 떄 실행 구문을 적을 수 있습니다.
			$(data).each(function(){
				// 하나하나 반복되는 각 데이터는 this라는 키워드로 표현됩니다.
				//console.log("-------------------");
				//console.log(this);
				
				// <li> 형식으로 댓글 끼워 넣기
					//str += "<li>" + this.rno + " : " + this.reply +"</li>"; (간단버전)
				str += "<li data-rno='" + this.rno + "' class='replyLi'>"
				+ this.rno + ":" + this.reply
				+ "</li>";
			});
			
			// 디버깅 ( #replies 사이에 끼워넣을 수 있도록 console.log()로 디버깅 )
			console.log(str);
			
			
			// 비어있는 ul 태그에다가 str을 넣어준 것
			$("#replies").html(str);

			
		});
		
		
	// $.getJSON은 입력한 주소에 get 방식으로 요청을 넣습니다.
		
		
		

	</script>


	
	
	

</body>
</html>