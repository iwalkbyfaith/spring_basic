<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	잘 들어왔나 확인 -> ${board} <br/><hr><br/>

	<h1> ${board.bno}번 글 수정 폼</h1><br/>
	
	<form action="/board/boardUpdate" method="post">
		글 번호 <input type="number" name="bno" value="${board.bno}" readonly /> &emsp;&nbsp;
		작성일 <input type="text" value="${board.regdate}" readonly/> &emsp;&nbsp;&nbsp;&nbsp;
		수정일 <input type="text" value="${board.updatedate}" readonly/> <br/><br/>
		글 제목 <input type="text" name="title" size=55 value="${board.title}"/> &emsp;&nbsp;&nbsp;&nbsp;
		글쓴이 <input type="text" value="${board.writer}" readonly/> <br/><br/>
		<textarea cols=100 rows=20 name="content">${board.content}</textarea> <br/><br/>
		<input type="reset" value="다시 작성하기"/>
		
		<!-- 04.15 수정 완료 후에도 페이지번호, 검색조건, 검색어가 유지되도록 전달 받은 데이터를 폼으로 다시 남겨줘야합니다. -->
		<input type="hidden" name="searchType" value="${param.searchType}"/>
		<input type="hidden" name="keyword" value="${param.keyword}"/>
		<input type="hidden" name="pageNum" value="${param.pageNum}"/>
		
		<input type="submit" value="수정 완료"/> <!-- 제목, 내용, 글번호를 보냄 -->
	</form> <br/>
	
	<a href="http://localhost:8181/board/boardList"><button>글 목록으로</button></a>

</body>
</html>