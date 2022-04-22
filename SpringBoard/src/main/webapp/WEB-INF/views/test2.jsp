<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 집에서 연습하기 -->
	
	
	<!-- 1. 네이버, 다음 -->
	
	<ul id="naver"></ul>
	<ul id="daum"></ul>
	
	<button id="daumButton">다음 소환!</button>
	
	<!-- 구글 cdn 추가 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script type="text/javascript">
	
		let bno = 196636;
		
		$.getJSON("/replies/all/"+ bno, function(data){
			
			let str = "<a href='https://www.naver.com'>네이버</a>";
			console.log(str);
			
			$("#naver").html(str);	
			
		});
		
	$("#daumButton").on("click", function(){
		
		let str2 = "<a href='https://www.daum.net'/>다음</a>";
		
		$("#daum").html(str2);
		
	});	
	
	</script>
	
	
	<!-- ■ 댓글 버튼 눌러서 특정 글 번호의 댓글 조회하기 -->

	
	<ul id="replies"></ul>
	
	<button id="showRepl">리플보기</button>
	
	<script type="text/javascript">
	
		/*
		// 1. showRepl이라는 버튼을 클릭했을때
			// onClick이 걸려 있으므로, 실행 조건 = 클릭
		$("#showRepl").on("click", function(){
			
			let bno = 196636;
			
			// 2. 레스트 서버에서 아래의 주소로 data를 받아와서
			$.getJSON("/replies/all/"+bno, function(data){
				
				let str ="";	
				console.log(data);
				
				// 3. 받아온 data를 .each를 사용해서 향상된 for문처럼 사용하여 str 변수에 넣어줌
				$(data).each(function(){
					str += "<li data-rno='" + this.rno + "' class='replyLi'>"
					+ this.rno + ":" + this.reply
					+ "</li>";
					
					// (str이 어떤 순서로 출력되는지 확인하는 디버깅)
					// (★ 한 바퀴 돌때마다 크롬 콘솔창에서 str이라는 변수에 <li> 태그가 점차 누적되는 것을 확인할 수 있음)
					console.log(str);
					console.log("----------------------------");
					
				});
				
				// 4. replies라는 이름의 ul 태그 사이에 str을 끼워 넣는다.
				$("#replies").html(str);
				
			});			
		});
		*/
		
		
		// ■ 함수 선언
			//위의 코드를 getAllList()라는 메서드로 만들어 놓음
		function getAllList(){
			
			let bno = 196636;  // 선생님은 이 부분을 함수 바깥으로 빼심 (아마 나중에 파라미터 받을 일이 있으면 빼야할수도?)
			
			// 2. 레스트 서버에서 아래의 주소로 data를 받아와서
			$.getJSON("/replies/all/"+bno, function(data){
				
				let str ="";	
				console.log(data);
				
				// 3. 받아온 data를 .each를 사용해서 향상된 for문처럼 사용하여 str 변수에 넣어줌
				$(data).each(function(){
					str += "<li data-rno='" + this.rno + "' class='replyLi'>"
					+ this.rno + ":" + this.reply
					+ "</li>";
					
					// (str이 어떤 순서로 출력되는지 확인하는 디버깅)
					// (★ 한 바퀴 돌때마다 크롬 콘솔창에서 str이라는 변수에 <li> 태그가 점차 누적되는 것을 확인할 수 있음)
					console.log(str);
					console.log("----------------------------");
					
				});
				
				// 4. replies라는 이름의 ul 태그 사이에 str을 끼워 넣는다.
				$("#replies").html(str);
				
			});		
		}
		
		
		// ■ 함수 호출
			// 함수 호출 구문을 적어야 진자 실행된다. 함수 선언부는 작성한다고 해서 바로 실행되지 않음.
			// 1. 126번 라인을 실행 -> 2. 93번으로 이동해서 120까지 실행 -> 3. 실행이 끝나면 126번으로 되돌아옴
		getAllList();
			
		
		// 에러 해결하고 업로드 하기 위해 주석 한 줄 처리해봄
	
	</script>
	
	
	
	
	
	
	
	
	
	

</body>
</html>