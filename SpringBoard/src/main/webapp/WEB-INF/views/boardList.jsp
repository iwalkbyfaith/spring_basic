<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	잘 들어왔나 확인 : 	${boardList}
	
	<hr/><br/>
	
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>글 번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>수정일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td>${board.bno}</td>
					<td><a href="http://localhost:8181/boardList/${board.bno}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.regdate}</td>
					<td>${board.updatedate}</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>

</body>
</html>