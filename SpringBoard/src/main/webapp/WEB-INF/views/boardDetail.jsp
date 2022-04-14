<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<div class="container">
		<br/><h1>${board.bno}번 글 조회중</h1><br/>
	
		<div class="row">
			<div class="col-md-2">
				글 번호 <input type="number" value="${board.bno}" class="form-control"/> 
			</div>
			<div class="col-md-5">
				작성일 <input type="text" value="${board.regdate}" class="form-control"/> 
			</div>
			<div class="col-md-5">
				수정일 <input type="text" value="${board.updatedate}" class="form-control"/> <br/><br/>
			</div>
		
			<div class="col-md-8">
				글 제목 <input type="text" size=55 value="${board.title}" class="form-control"/> 
			</div>
			<div class="col-md-4">
				글쓴이 <input type="text" value="${board.writer}" class="form-control"/> <br/><br/>
			</div>
			<textarea cols=110 rows=20 class="form-control" >${board.content}</textarea> <br/><br/>
		
		</div><br/>	
		
		<!-- <a href="http://localhost:8181/boardList"><button>게시글 리스트</button></a> <br/><br/>  -->
	
	
		<div class="row">
		
			<div class="col-md-1">
				<!-- <a href="http://localhost:8181/boardList"><button>글 목록</button></a>  -->
				<!-- 부트스트랩 적용하면 아래와 같이도 가능 -->
				<a href="/boardList" class="btn btn-success">글 목록</a>
			</div>
			
			<!-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ -->
			<!-- ■ 04.14 추가 들어온 페이지로 다시 돌아가는 버튼 -->
			<!-- 컨트롤러에서 /boardDetail은 SearchCriteria를 굳이 받을 필요가 없고, 버튼에다가 직접 하나하나 입혀주는 방법을 사용하면 된다. -->
			<!-- => 검색 키워드 : ● 쿼리 스트링 방식 (뒤쪽에다 붙이는 방식). 쿼리 스트링으로 받아오는 방식은 'pram.뭐시기' 로 받을 수 있음 -->
			<!-- 진입하는 링크에서 보낸 데이터를 $param.이름}으로 받을 수 있다. param 왼쪽에 { 이거 붙이니까 주석인데도 에러나서 잠깐 뺐음 -->
			<div class="col-md-8">
				<!-- <a href="http://localhost:8181/boardList"><button>글 목록</button></a>  -->
				<!-- 부트스트랩 적용하면 아래와 같이도 가능 -->
				<a href="http://localhost:8181/boardList?pageNum=${param.pageNum}&searchType=${param.searchType}&keyword=${param.keyword}" class="btn btn-success">뒤로가기</a>
			</div>
			<!-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ -->
			
			<div class="col-md-2">
				<!-- 수정 버튼 -->
				<form action="/boardUpdateForm" method="post">
					<input type="hidden" name="bno" value="${board.bno}"/>
					<input type="submit" value="수정하기" class="btn btn-warning"/>
				</form>
			</div>
			<div class="col-md-1">
				<!-- 삭제 버튼 -->
				<form action="/boardDelete" method="post">
					<input type="hidden" name="bno" value="${board.bno}"/>
					<input type="submit" value="삭제하기" class="btn btn-danger"/>
				</form>
			</div>
			
			
			
		</div>
		
	</div>

</body>
</html>