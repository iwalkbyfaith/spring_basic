<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- ★ 부트스트랩 1. css 복붙 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<!-- ★ 부트스트랩 2. 번들 복붙 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<!-- ■ 04.26 스타일 태그로 모달의 모양을 잡아줌 -->
	<style> 
		#modDiv{ 
			width: 300px;
			height: 100px;
			background-color: green;
			position: absolute;
			top: 50%;
			left: 50%;
			margin-top: -50px;
			margin-left: -150px;
			padding: 10px;
			z-index: 1000;
		}
	</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Ajax 테스트 (댓글 등록 화면 테스트) 교안(09) 11쪽 </h2>
	
	
	
	<!-- ■ 04.25 댓글 목록과 댓글쓰기 창이 한 화면으로 보이도록 수정 -->	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- ■ 댓글이 추가될 공간 -->
	<ul id="replies"></ul>
	
	
	<!-- ■ 댓글 작성 공간 -->
	<div>
		<div> <!-- name이 안 적혀도 상관 없음 (빼도 됨) -->
			댓쓴이 <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			댓글 <input type="text" name="reply" id="newReply">
		</div>
		<button id="replyAddBtn">댓글 추가</button>
	
	</div>
	
	<!-- ■ 04.26 모달 창 만들기 -->
	<!-- modal은 일종의 팝업입니다.
		 단, 새 창을 띄우지는 않고 css를 이용해, 특정 태그가 조건부로 보이거나 안보이도록 처리해서 마치 창이 새로 띄워지는 것처럼 만듭니다.
		 따라서 눈에 보이지는 않아도 modal을 구성하는 태그 자체는 화면에 미리 적혀있어야 합니다.
		 모달 내부는 만들고 싶은대로 만들면 된다.-->
	
	<div id="modDiv" style="display:none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="reply">
		</div>
		<div>
			<button type="button" id="replyModBtn">수정</button> <!-- 버튼 태그이기 때문에 type="button"을 안 적어도 된다. -->
			<button type="button" id="replyDelBtn">삭제</button>
			<button type="button" id="closeBtn">모달창 닫기</button>
		</div>
	</div>
	
	
	
	<!-- ■ '위임' 이벤트 이해를 위한 코드 (삭제 예정) -->
	<button class="test">테스트1</button>
	<button class="test">테스트2</button>
	<button class="test">테스트3</button>
	<button class="test">테스트4</button>
	
	
	<!-- ■  1. jquery cdn 가져오기 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- ■  2. 스크립트 태그로 자바 스크립트 요청 확인 -->
	<script>
	
		let bno = 196636;
		
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
					+ "<button>수정/삭제</button></li>";
					//+ "</li>";
					
					// (str이 어떤 순서로 출력되는지 확인하는 디버깅)
					// (★ 한 바퀴 돌때마다 크롬 콘솔창에서 str이라는 변수에 <li> 태그가 점차 누적되는 것을 확인할 수 있음)
					console.log(str);
					console.log("----------------------------");
					
				});
				
				// 4. replies라는 이름의 ul 태그 사이에 str을 끼워 넣는다.
				$("#replies").html(str);
				
			});		
		}
		
		// 댓글 전체를 들과 와서 #replies에 심어주는 로직 실행
		getAllList();
	
		
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
						// 왼쪽의 bno는 #하고 {bno}고, 오른쪽의 bno는 위의 let bno
						bno : bno,
						replyer : replyer,
						reply : reply
					}),
					// ● 실행에 성공했을때 뭘 할거냐?
					success : function(result){
						if(result == 'SUCCESS'){
							alert("등록 되었습니다");
							
							// ● (04.25) 댓글 등록 성공시, 다시 목록 생신
							getAllList();
							// ● (04.25) 폼 태그 비우기
							$("#newReplyWriter").val("");
							$("#newReply").val("");
						}
					}
					// ● 에러 난 경우의 코드는 지금은 어려울까봐 잠깐 빼신다고
				});
		
		});
		
		
		// ■ 04.26 이벤트 위임       // 여기서 .replyLi를 빼고 button 태그만 입력해도 정상적으로 작동한다.
		$("#replies").on("click", ".replyLi button", function(){
			
			// 1. 클릭한 버튼과 연계된 li 태그를 replyTag 변수에 저장
			var replyTag = $(this).parent();
				// 클릭한 요소가 this이고, 현재 button에 걸렸기 때문에 this는 button이다.
				// button의 부모가 바로 .replyLi이다.
				// 즉, 클릭한 버튼과 연계된 li 태그를 replyTag 변수에 저장한다.
				console.log(replyTag); // alert를 잠깐 주석처리하면 콘솔창에 뜸
				
			// 2. 글 번호 얻어오기
				// 원하는 실제 정보는 버튼 태그가 아니라, li 태그에 있다.
				// 클릭한 버튼과 연계된 li 태그의 data-rno에 든 값을 가져와 변수 rno에 저장하기.
			var rno = replyTag.attr("data-rno");
				console.log(rno);
				
			// 3. 본문 가져오기
			var reply = replyTag.text();
				console.log(reply);
			
			//alert(rno + " : " + reply);
			
			// ● 04.26 모달 추가
			$(".modal-title").html(rno);  // modal-title 부분에 글 번호 입력
			$("#reply").val(reply);		  // reply 영역에 리플 내용을 기입 (수정/혹은 삭제니까)
			$("#modDiv").show("slow");	  // 버튼 누르면 모달이 열리게 됨
		});
		
		
		// ● 04.26 모달 창 닫기
			// 모달은 .show()로 열 수 있고, .hide로 닫을 수 있다.
		$("#closeBtn").on("click", function(){
			$("#modDiv").hide("slow");
		});
		
		
		// ■ 04.26 삭제 버튼 작동
		$("#replyDelBtn").on("click", function(){
			let rno = $(".modal-title").html();
			
			$.ajax({  // json 형식으로 요청
				type : 'delete',
				url : '/replies/' + rno,
				header : {"X-HTTP-Method-Override" : "DELETE"},
				dataType : 'text',
				success : function(result){
					console.log("result:  " + result);
					if(result == 'SUCCESS'){
						alert("삭제되었습니다");		 // 창 띄워주면 좋으니까
						$("#modDiv").hide("slow");	 // 모달 창 숨기기
						getAllList(); 				 // 삭제된 댓글 반영한 새 댓글 목록 갱신
					}
				}
			});	
		});
		
		// ■ 04.26 수정 버튼 작동
		$("#replyModBtn").on("click", function(){
			let rno = $(".modal-title").html();
			let reply = $("#reply").val();			// 댓글 내용을 가져와서 넣어줘야 수정 가능
			
			$.ajax({
				type : 'patch', // put으로 해도 됨 (지금 레벨에서는 큰 차이가 없음)
				url : '/replies/' + rno,
				header : {
					"Content-Type" : "application/json", // json 자료를 추가로 입력받기 때문에, json을 쓴다고 명시
					"X-HTTP-Method-Override" : "PATCH"
				},
				contentType : "application/json",
				data : JSON.stringify({reply:reply}),
				dataType : 'text',
				success : function(result){
					console.log("result: " + result);
					if(result == 'SUCCESS'){
						alert("수정 되었습니다");
						$("#modDiv").hide("slow");
						getAllList();
					}
				}
				
			});
		});
		

			

	</script>
	
	
	
	<script>
		// '위임' 이해를 위한 실습
		// 1. .test를 클릭하면 "테스트 클릭 감지"라는 alert를 띄우도록 이벤트를 걸어보세요
		// 2. 클릭 요소의 텍스트까지 같이 띄워주세요 ex : 테스트1 클릭 감지. => this.라는 키워드를 추가해줌
			// this만 콘솔에 출력하는 것은 가능하다. 문제는 내부에 있는 데이터(테스트1, 테스트2..)를 얻어올 수 없음
			// 뭘 클릭했는지 감지는 하나, 내부 데이터를 활용하기 어렵다는 것
		
		$(".test").on("click", function(){
			console.log($(".test").html()); // 이렇게하면 어떤 버튼을 눌러도 '테스트1'만 나옴
			console.log(this);
			//console.log(this.html);  // 내부에 있는 요소를 가져옴. 그러나 콘솔에서 함수가 아니라고 에러뜨면서 안 나옴.
			alert(this + "테스트 클릭 감지");
		});
		

	</script>

</body>
</html>