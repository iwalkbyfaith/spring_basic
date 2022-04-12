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
	
	
	<div class="container">
		<br/><h1>보드 리스트</h1><br/>
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
						<td><a href="http://localhost:8181/boardDetail/${board.bno}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.regdate}</td>
						<td>${board.updatedate}</td>
					</tr>
				</c:forEach>
			</tbody>
		
		</table>
	

		<!-- <a href="/boardInsert"><button>글 쓰기</button></a>  -->
		<!-- 이런 방법도 있음 (부트스트랩 적용시) -->
		<a href="/boardInsert" class="btn btn-success">글쓰기</a>
		
	</div>
	
	
	<hr/>
	디버깅용 => ${pageMaker}
	<hr/>
	
	<!-- 04.12 부트스트랩에서 페이지네이션 버튼을 깔아보세요 -->
	
	■ 시도1) currentPage를 포워딩 받고, 볼드처리 된 버튼
	<nav aria-label="navi">
	  <ul class="pagination justify-content-center">
	  	<c:if test="${pageMaker.prev}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.startPage-1}"><span class="page-link">Previous</span></a>
		    </li>
	    </c:if>
	    
		<c:forEach var="index" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<c:if test="${currentPage eq index}">
				<li class="page-item"><a class="page-link" href="/boardList?pageNum=${index}"><b>${index}</b></a></li>
			</c:if>
			<c:if test="${currentPage ne index}">
				<li class="page-item"><a class="page-link" href="/boardList?pageNum=${index}">${index}</a></li>
			</c:if>
		</c:forEach>
	    
	    <c:if test="${pageMaker.next}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.endPage+1}"><span class="page-link">Next</span></a>
		    </li>	
	    </c:if>
	  </ul>
	<nav>
	
	<hr/> <!--  ------------------------ -->
	
	■ 시도2) currentPage를 포워딩 받고, active 처리된 버튼
	<nav aria-label="navi">
	  <ul class="pagination justify-content-center">
	  	<!-- ● 이전 페이지 버튼 -->
	  	<c:if test="${pageMaker.prev}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.startPage-1}"><span class="page-link">Previous</span></a>
		    </li>
	    </c:if>
	    
	    <!-- ● 숫자 버튼 -->
		<c:forEach var="index" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<!-- ● 클릭한 해당 페이지를 active 처리해주기 -->
			<c:if test="${currentPage eq index}">
				<li class="page-item active"><a class="page-link" href="/boardList?pageNum=${index}">${index}</a></li>
			</c:if>
			<c:if test="${currentPage ne index}">
				<li class="page-item"><a class="page-link" href="/boardList?pageNum=${index}">${index}</a></li>
			</c:if>
		</c:forEach>
	    
	    <!-- ● 다음 페이지 버튼 -->
	    <c:if test="${pageMaker.next}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.endPage+1}"><span class="page-link">Next</span></a>
		    </li>	
	    </c:if>
	  </ul>
	<nav>
	
	
	
	
	<hr/>
	
	■ 시도3) currentPage를 포워딩 안 받고 pageMaker.cri.pageNum로 처리하기
	<nav aria-label="navi">
	  <ul class="pagination justify-content-center">
	  	<!-- ● 이전 페이지 버튼 -->
	  	<c:if test="${pageMaker.prev}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.startPage-1}"><span class="page-link">Previous</span></a>
		    </li>
	    </c:if>
	    
	    <!-- ● 숫자 버튼 -->
		<c:forEach var="index" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<!-- ● 클릭한 해당 페이지를 active 처리해주기 -->
			<c:if test="${pageMaker.cri.pageNum eq index}">
				<li class="page-item active"><a class="page-link" href="/boardList?pageNum=${index}">${index}</a></li>
			</c:if>
			<c:if test="${pageMaker.cri.pageNum ne index}">
				<li class="page-item"><a class="page-link" href="/boardList?pageNum=${index}">${index}</a></li>
			</c:if>
		</c:forEach>
	    
	    <!-- ● 다음 페이지 버튼 -->
	    <c:if test="${pageMaker.next}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.endPage+1}"><span class="page-link">Next</span></a>
		    </li>	
	    </c:if>
	  </ul>
	<nav>
	
	
	<hr/>
	
	■ 시도4) currentPage를 포워딩 안 받고 pageMaker.cri.pageNum로 처리하기. (3항 연산자로 처리해보기) (추천)
	<nav aria-label="navi">
	  <ul class="pagination justify-content-center">
	  	<!-- ● 이전 페이지 버튼 -->
	  	<c:if test="${pageMaker.prev}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.startPage-1}"><span class="page-link">Previous</span></a>
		    </li>
	    </c:if>
	    
	    <!-- ● 숫자 버튼 -->
		<c:forEach var="index" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<!-- ● 클릭한 해당 페이지를 active 처리해주기 (3항 연산자) -->
				<li class="page-item ${pageMaker.cri.pageNum eq index ? 'active' : '' }"><a class="page-link" href="/boardList?pageNum=${index}">${index}</a></li>
		</c:forEach>
	    
	    <!-- ● 다음 페이지 버튼 --> <!-- endPage == 0 인 경우(글이 하나도 없을때)도 고려해주어야 -->
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		    <li class="page-item disabled">
		      <a href="boardList?pageNum=${pageMaker.endPage+1}"><span class="page-link">Next</span></a>
		    </li>	
	    </c:if>
	  </ul>
	<nav>
	
	

</body>
</html>