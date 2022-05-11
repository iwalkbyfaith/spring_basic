<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>회원가입창</h1>
	<form action="/secu/join" method="post">
		<input type="text" name="userId" placeholder="아이디"/><br/> <!-- MemberVO 내부의 변수명과 같게 해야함 -->
		<input type="text" name="userPw" placeholder="비밀번호"/><br/>
		<input type="text" name="userName" placeholder="이름"/><br/>
		
		<input type="checkbox" name="role" value="ROLE_ADMIN"/> 어드민 권한  &nbsp;&nbsp;&nbsp; 	<!-- 실제로 이렇게 권한 체크 안함. -->
		<input type="checkbox" name="role" value="ROLE_MEMBER"/> 정회원 권한 &nbsp;&nbsp;&nbsp;	<!-- 작업할 때 만들기 편하라고 체크박스 만듦 -->
		<input type="checkbox" name="role" value="ROLE_USER"/> 준회원 권한 <br/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="가입하기"/>
	</form>

</body>
</html>