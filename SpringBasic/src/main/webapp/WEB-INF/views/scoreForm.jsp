<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/score" method="post">
		<input type="number" name="math" placeholder="수학"/><br/>
		<input type="number" name="eng" placeholder="영어"/><br/>
		<input type="number" name="kor" placeholder="언어"/><br/>
		<input type="number" name="soc" placeholder="사회"/><br/>
		<input type="number" name="com" placeholder="컴퓨터"/><br/>
		<input type="submit" value="성적 제출하기"/>
	</form>

</body>
</html>