<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="uploadFormAction" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple> <!-- 컨트롤러에서 uploadFile 이라는 이름으로 받으면 이름 뿐만아니라 용량도 감지함 -->
		<button>Submit</button>						   <!-- ★ 같은 파일명이면 이전 파일을 밀어버리고 대체함(이전 파일이 삭제된다) -->
	</form>

</body>
</html>