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

	잘 들어왔나 확인 : 	${board}
	
	<hr/><br/>
	
	글 번호 <input type="number" value="${board.bno}"/> &emsp;&nbsp;
	작성일 <input type="text" value="${board.regdate}"/> &emsp;&nbsp;&nbsp;&nbsp;
	수정일 <input type="text" value="${board.updatedate}"/> <br/><br/>
	글 제목 <input type="text" size=55 value="${board.title}"/> &emsp;&nbsp;&nbsp;&nbsp;
	글쓴이 <input type="text" value="${board.writer}"/> <br/><br/>
	<textarea cols=100 rows=20>${board.content}</textarea> <br/><br/>
	
	<a href="http://localhost:8181/boardList"><button>뒤로 가기</button></a>
	

</body>
</html>