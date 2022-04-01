<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	${user}
	
	<hr/><br/>
	
	유저 번호 -> ${user.userNum } <br/>
	유저 아이디 -> ${user.userId} <br/>
	유저 비밀번호 -> ${user.userPw } <br/>
	유저 이름 -> ${user.userName } <br/>
	유저 나이 -> ${user.userAge } <br/>
	
	<hr/><br/>
	
	<table border="1">
		<thead>
			<tr>
				<th> 유저 번호 </th>
				<th> 아이디 </th>
				<th> 비밀번호 </th>
				<th> 이름 </th>
				<th> 나이 </th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${user.userNum }</td>
				<td>${user.userId}</td>
				<td>${user.userPw }</td>
				<td>${user.userName }</td>
				<td>${user.userAge }</td>
			</tr>
		</tbody>
	</table>

</body>
</html>