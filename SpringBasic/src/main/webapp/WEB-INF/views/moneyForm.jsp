<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/getMoney" method="post"> <!-- 혹은 http://localhost:8181/getMoney -->
		<input type="number" name="won" placeholder="환전할 금액(원)"/>
		<input type="submit" value="환전하기"/>
	</form>

</body>
</html>