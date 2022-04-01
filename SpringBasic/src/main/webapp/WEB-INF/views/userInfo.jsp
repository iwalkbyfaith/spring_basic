<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/userInfo" method="post">
		<input type="number" name="userNum" placeholder="유저 번호"/> <br/>
		<input type="text" name="userId" placeholder="유저 아이디"/><br/>
		<input type="text" name="userPw" placeholder="유저 비밀번호"/><br/>
		<input type="text" name="userName" placeholder="유저 이름"/><br/>
		<input type="number" name="userAge" min="1" placeholder="유저 나이"/><br/>
		<input type="reset"/>
		<input type="submit"/>
	
	</form>

</body>
</html>