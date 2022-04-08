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
	
	<form action="/boardUpdate" method="post">
		글 번호 <input type="number" name="bno" value="${board.bno}" readonly /> &emsp;&nbsp;
		작성일 <input type="text" value="${board.regdate}" readonly/> &emsp;&nbsp;&nbsp;&nbsp;
		수정일 <input type="text" value="${board.updatedate}" readonly/> <br/><br/>
		글 제목 <input type="text" name="title" size=55 value="${board.title}"/> &emsp;&nbsp;&nbsp;&nbsp;
		글쓴이 <input type="text" value="${board.writer}" readonly/> <br/><br/>
		<textarea cols=100 rows=20 name="content">${board.content}</textarea> <br/><br/>
		<input type="reset" value="다시 작성하기"/>
		<input type="submit" value="수정 완료"/> <!-- 제목, 내용, 글번호를 보냄 -->
	</form> <br/>
	
	<a href="http://localhost:8181/boardList"><button>글 목록으로</button></a>

</body>
</html>