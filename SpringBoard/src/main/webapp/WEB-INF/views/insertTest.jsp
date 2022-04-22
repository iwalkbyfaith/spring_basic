<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Ajax 테스트 (댓글 등록 화면 테스트) 교안(09) 11쪽 </h2>
	
	<div>
		<div> <!-- name이 안 적혀도 상관 없음 (빼도 됨) -->
			댓쓴이 <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			댓글 <input type="text" name="reply" id="newReply">
		</div>
		<button id="replyAddBtn">댓글 추가</button>
	
	</div>
	
	<!-- 1. jquery cdn 가져오기 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- 2. 스크립트 태그로 자바 스크립트 요청 확인 -->
	<script>
	
		let bno = 196636;
	
		
		$("#replyAddBtn").on("click", function(){
			
			// ● 폼이 없기 때문에, input 태그 내에 입력된 요소를 가져와야 한다.
				// .val()은 해당 요소 내에 적혀 있는 문자를 들고온다.
				// 버튼을 누르는 시점에, 글쓴이와 내용 내부에 적힌 문자열을 변수에 저장한다.
				// 중괄호 열고 닫고 안에서는 무조건 콜론(:) 사용! '='을 사용하면 에러남
			let replyer = $("#newReplyWriter").val();
			let reply = $("#newReply").val();
			
				// ● 비동기 요청
					// $.ajax({내용물}); 이 기본 형태
					// 한 줄이 끝날때마다 콤마(,)를 적어주는 것을 잊지 말아야 함.
				$.ajax({
					type : 'post',
					url : '/replies',
					headers: {
						"Content-Type" : "application/json", // json 데이터 넣어줌
						"X-HTTP-Method-Override" : "POST"    // 무슨 메서드인지 또 적어줌
					},
					dataType : 'text',
					data : JSON.stringify({ 
						// 밑의 데이터들은 xml 파일에 가서 구멍이 몇개 뚫린지 확인해서 넣어주면 됨
						// 왼쪽의 bno는 #{bno}이고, 오른쪽의 bno는 위의 let bno
						bno : bno,
						replyer : replyer,
						reply : reply
					}),
					// ● 실행에 성공했을때 뭘 할거냐?
					success : function(result){
						if(result == 'SUCCESS'){
							alert("등록 되었습니다");
						}
					}
					// ● 에러 난 경우의 코드는 지금은 어려울까봐 잠깐 빼신다고
				});
		
		});

	</script>

</body>
</html>