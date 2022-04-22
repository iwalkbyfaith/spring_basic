<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>ajax 테스트 (연습용 파일로 하나 더 만듦), 네이버/다음(버튼)</h2>
	
	<!-- ■ ul태그 replies -->
	<ul id="replies">
		<!-- 비어있는 ul -->
	</ul>
	
	<!-- ■ ul태그 test -->
	<!-- #test 사이에 daum.net으로 이동하는 a태그를 jquery를 이용해 넣어주세요 -->
	<ul id="test"></ul>
	
	<!-- ■ 버튼 만들어서 연결해보기 -->
	<button id="testBtn">테스트 버튼</button>
	
	<!-- ■ jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		
	
	<!-- ■ #test 실습 -->
	<script type="text/javascript">
		let bno = 196636;
		
				  //주소(첫번째 파라미터)    // (두번째 파라미터)콜백함수 주소 요청으로 얻어온 json을 어떻게 처리할지
		$.getJSON("/replies/all/" + bno, function(data){ // REST 컨트롤러에 get 방식으로 요청을 넣음
			
			// 1. #test에 넣어줄 문자를 생성한다.
			let str = "<a href='https://www.naver.com/'>네이버로 넘어가기</a>"
			console.log(data);
			
			// 2. #test를 jquery로 잡고, 태그 내부에 str을 넣어준다.
			$("#replies").html(str);
			
		});
				  
		
	<!-- ■ 버튼(testBtn) 클릭시 발동되는 이벤트 -->
	// testBtn 클릭시 49~57번 라인 실행
				 // testBtn 클릭시  // 뒤쪽 함수(49,50,51,52,53,54,55,56,56) 실행
	$("#testBtn").on("click", function(){
			
		// 1. #test에 넣어줄 문자를 생성한다.
			let str2 = "<a href='https://www.daum.net/'>다음으로 넘어가기</a>"
			
		// 2. #test를 jquery로 잡고, 태그 내부에 str2를 넣어준다.
			$("#test").html(str2);
			
		});
	
	
	</script>

</body>
</html>