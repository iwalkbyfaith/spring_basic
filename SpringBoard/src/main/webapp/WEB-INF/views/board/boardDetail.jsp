<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<!-- ★ 부트스트랩 1. css 복붙 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<!-- ★ 부트스트랩 2. 번들 복붙 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<!-- ■ 04.27 디테일 페이지에 추가하기 위해 insertTest.jsp에서 복붙 -->
	<style> 
		#modDiv{ 
			width: 500px;
			height: 100px;
			background-color: green;
			border : black solid 1px;
			position: absolute;
			top: 50%;
			left: 50%;
			margin-top: -50px;
			margin-left: -250px;
			padding: 10px;
			z-index: 1000;
		}
		
		#reply{ width: 450px;}
	</style>
	

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<div class="container">
		<br/><h1>${board.bno}번 글 조회중</h1><br/>
	
		<div class="row">
			<div class="col-md-2">
				글 번호 <input type="number" value="${board.bno}" class="form-control" readonly/> 
			</div>
			<div class="col-md-5">
				작성일 <input type="text" value="${board.regdate}" class="form-control" readonly/> 
			</div>
			<div class="col-md-5">
				수정일 <input type="text" value="${board.updatedate}" class="form-control" readonly/> <br/><br/>
			</div>
		
			<div class="col-md-8">
				글 제목 <input type="text" size=55 value="${board.title}" class="form-control" readonly/> 
			</div>
			<div class="col-md-4">
				글쓴이 <input type="text" value="${board.writer}" class="form-control" readonly/> <br/><br/>
			</div>
			<textarea cols=110 rows=20 class="form-control" readonly >${board.content}</textarea> <br/><br/>
		
		</div><br/>	
		
		<!-- <a href="http://localhost:8181/boardList"><button>게시글 리스트</button></a> <br/><br/>  -->
	
	
		<div class="row">
		
			<div class="col-md-1">
				<!-- <a href="http://localhost:8181/boardList"><button>글 목록</button></a>  -->
				<!-- 부트스트랩 적용하면 아래와 같이도 가능 -->
				<a href="/board/boardList" class="btn btn-success">글 목록</a>
			</div>
			
			<!-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ -->
			<!-- ■ 04.14 추가 들어온 페이지로 다시 돌아가는 버튼 -->
			<!-- 컨트롤러에서 /boardDetail은 SearchCriteria를 굳이 받을 필요가 없고, 버튼에다가 직접 하나하나 입혀주는 방법을 사용하면 된다. -->
			<!-- => 검색 키워드 : ● 쿼리 스트링 방식 (뒤쪽에다 붙이는 방식). 쿼리 스트링으로 받아오는 방식은 'pram.뭐시기' 로 받을 수 있음 -->
			<!-- 진입하는 링크에서 보낸 데이터를 $param.이름}으로 받을 수 있다. param 왼쪽에 { 이거 붙이니까 주석인데도 에러나서 잠깐 뺐음 -->
			<div class="col-md-8">
				<!-- <a href="http://localhost:8181/boardList"><button>글 목록</button></a>  -->
				<!-- 부트스트랩 적용하면 아래와 같이도 가능 -->
				<a href="http://localhost:8181/board/boardList?pageNum=${param.pageNum == null? 1 : param.pageNum}&searchType=${param.searchType}&keyword=${param.keyword}" class="btn btn-success">뒤로가기</a>
			</div>
			<!-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ -->
			
			<div class="col-md-2">
				<!-- 수정 버튼 --><!-- 04.15 값 추가 -->
				<form action="/board/boardUpdateForm" method="post">
					<input type="hidden" name="bno" value="${board.bno}"/>
					<input type="hidden" name="searchType" value="${param.searchType}"/>
					<input type="hidden" name="keyword" value="${param.keyword}"/>
					<input type="hidden" name="pageNum" value="${param.pageNum}"/>
					<input type="submit" value="수정하기" class="btn btn-warning"/>
				</form>
			</div>
			<div class="col-md-1">
				<!-- 삭제 버튼 -->
				<form action="/board/boardDelete" method="post">
					<input type="hidden" name="bno" value="${board.bno}"/>
					<input type="hidden" name="searchType" value="${param.searchType}"/>
					<input type="hidden" name="keyword" value="${param.keyword}"/>
					<input type="hidden" name="pageNum" value="${param.pageNum}"/>
					<input type="submit" value="삭제하기" class="btn btn-danger"/>
				</form>
			</div>
			
			
			
		</div>
		
	
	
	
	
	
	
		<!-- 04.27 댓글 추가하기 -->
		
		
		<!-- 여기부터 댓글 비동기 처리 자바스크립트 처리 영역 -->
		<!-- ● 해야 할 일
			 1. 댓글 작성 공간(modal)
			 2. 댓글 추가 공간(#replies)
			 3. 자바스크립트 코드들
			 4. 부트스트랩 css와 부트스트랩 js를 적절히 복붙
		 -->
		 
		 	<!-- ■ jqueryCDN 추가 -->
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		 
		 	<!-- ■ 댓글이 추가될 공간 -->
		 	<div class="row">
		 		<h3 class="text-primary">댓글</h3>
				<div id="replies"><!-- 댓글이 들어갈 위치 --></div>
			</div>
			
			<!-- ■ 댓글 작성 공간 -->
			<div class="row box-box-success">
				<div class="box-header">
					<h2 class="text-primary"> 댓글 작성</h2>
				</div><!-- header 끝 -->
				<div class="box-body">
					<strong>Writer</strong>
					<input type="text" id="newReplyWriter" placeholder="댓쓴이" class="form-control">
					<strong>Reply</strong>
					<input type="text" id="newReply" placeholder="댓글 작성" class="form-control">
				</div><!-- body 끝 -->
				<div class="box-footer">
					<button type="button" class="btn btn-success" id="replyAddBtn"> 댓글 작성 </button>
				</div><!-- footer 끝 -->
			</div>
		
	</div> <!-- div container 태그를 여기로 옮겨서 댓글도 왼쪽에 치우치지 않게 하기 -->
		
		<!-- ■ 04.26 모달 창 만들기 -->
		<!-- modal은 일종의 팝업입니다.
			 단, 새 창을 띄우지는 않고 css를 이용해, 특정 태그가 조건부로 보이거나 안보이도록 처리해서 마치 창이 새로 띄워지는 것처럼 만듭니다.
			 따라서 눈에 보이지는 않아도 modal을 구성하는 태그 자체는 화면에 미리 적혀있어야 합니다.
			 모달 내부는 만들고 싶은대로 만들면 된다.-->
		
		<div id="modDiv" style="display:none;">
			<div class="modal-title"></div>
			<div>
				<input type="text" id="reply"">
			</div>
			<div>
				<button type="button" id="replyModBtn">수정</button> <!-- 버튼 태그이기 때문에 type="button"을 안 적어도 된다. -->
				<button type="button" id="replyDelBtn">삭제</button>
				<button type="button" id="closeBtn">모달창 닫기</button>
			</div>
		</div>
		
	 
	<script>
		// 그동안은 고정 번호를 사용했는데, 이제는 그때그때 들어가는 글에 따라서 들어가면 되는 것	
		let bno = ${board.bno};
		
		function getAllList(){
							
			// 2. 레스트 서버에서 아래의 주소로 data를 받아와서
			$.getJSON("/replies/all/"+bno, function(data){
				
				let str ="";	
				console.log(data);
				
				// 3. 받아온 data를 .each를 사용해서 향상된 for문처럼 사용하여 str 변수에 넣어줌 
				//    (04.27 변한 코드는 교안(09) 29쪽 참고)
				$(data).each(function(){
					
					// ● 날짜 처리를 위해 자바스크립트의 Date 객체를 이용
						// 188번 코드의 우항에 this.updateDate를 timestamp라는 변수에 넣어서 (여기까지 '유닉스' 시간으로 들어옴)
						// 사람이 보는 시간으로 고치기 위해 timestamp를 Date에다가 넣어준다.
						// 그럼 변형 가능한 형태로 저장이 되어 date 변수에 저장되고, .getFullYear().. 같은 메서드를 사용해서 써주면 된다.
					let timestamp = this.updateDate;
					let date = new Date(timestamp);
					
					// 최종 수정 시간이 표시됨(받아온게 .updateDate니까)
						// 작성일을 쓰고 싶으면 .replyDate를 받아와서 변수 하나 더 만들어서 사용해야함
					let formattedTime = "게시일 : " + date.getFullYear()	 // 년도
										+ "/" + (date.getMonth() + 1)	 // 월은 0부터 시작하니까 더하기 1
										+ "/" + date.getDate()			 // 일
										+ "-" + date.getHours()			 // 시
										+ "시" + date.getMinutes()	     // 분
										+ "분" + date.getSeconds()		 // 초
										+ "초"
										
				
					str += "<div class='replyLi' data-rno='" + this.rno + "'><strong>@"
							+ this.replyer + "</strong> - " + formattedTime + "<br>"   // formattedTime을 넣었기에 유닉스X, 실제 시간
							+ "<div class='reply'>" + this.reply + "</div>"
							+ "<button type='button' class='btn btn-info'>수정/삭제</button>" 
							+ "</div></br>";
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
		
		getAllList();
		
		
		// ■ 댓글 추가 버튼 로직
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
				let replyTag = $(this).parent();
				// 클릭한 요소가 this이고, 현재 button에 걸렸기 때문에 this는 button이다.
				// button의 부모가 바로 .replyLi이다.
				// 즉, 클릭한 버튼과 연계된 li 태그를 replyTag 변수에 저장한다.
				
			// ● 04.27 추가
				// this(button)의 부모(.replyLi)가 아닌, 형제 태그 .reply의 내용을 대신 가져올 수 있도록 
				// 변수 replyContent를 선언해 거기에 저장해주세요. (힌트 : .siblings("요소명")을 사용)
				let replyContent = $(this).siblings(".reply");
				
					// 다른 방법1) : $(this).prev().text()를 해도 됨 				 (button의 형제 태그인 .reply를 가져오는 것)
					// 다른 방법2) : $(this).parent().children(".reply").text()    (button의 부모태그의 자식 태그를 가져오기)
					//			   선생님은 replyContext에 애초에 .text()를 붙이셨고, 나는 아래 reply에 .text()를 붙였다는 차이가 있음
				
				console.log(replyTag); // alert를 잠깐 주석처리하면 콘솔창에 뜸
				
				// ● 04.27 (댓글 내용 디버깅)
				console.log("댓글 내용 -> "+ replyContent.text());
				
			// 2. 글 번호 얻어오기
				// 원하는 실제 정보는 버튼 태그가 아니라, li 태그에 있다.
				// 클릭한 버튼과 연계된 li 태그의 data-rno에 든 값을 가져와 변수 rno에 저장하기.
			let rno = replyTag.attr("data-rno");
				console.log(rno);
				
			// 3. 본문 가져오기
				//var reply = replyTag.text();
			// ● 04.27 replyTag -> replyContent
			let reply = replyContent.text();
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
			let rno = $(".modal-title").html();		// 댓글 번호를 노출시키는게 싫다면, 우항의 코드를 사용하지 않고 아래 코드로 바꾸면 됨
													// replyTag.attr("data-rno");. 당연히 모달 타이틀 사이에 들어가지 않게 코드도 처리해주어야겠지
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
	
	
	
	
	

</body>
</html>