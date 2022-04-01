<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>성적 확인란</h1>
	
	<h2>과목별 성적</h2>
	<h3>수학 성적 : ${math}</h3>
	<h3>영어 성적 : ${eng}</h3>
	<h3>언어 성적 : ${kor}</h3>
	<h3>사회 성적 : ${soc}</h3>
	<h3>컴퓨터 성적 : ${com}</h3>
	<hr>
	<h2>총점과 평균</h2>
	<h3>평균 점수 : ${avg}</h3>
	<h3>총점 : ${total}</h3>

</body>
</html>