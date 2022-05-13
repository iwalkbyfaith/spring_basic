<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>			    <!-- ◀ 태그라이브러리 사용 -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 	<!-- ◀ 시큐리티에서 따로 써줘야함 -->
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	<!-- ◀ 에러 메세지를 출력하는 .jsp 파일! view 폴더 바로 아래에 만들어야함 -->
	
	<h1>접근 실패!</h1>
	<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}"/></h2>		<!-- 출력 메세지 : Access is denied -->
																				<!-- 스프링 시큐리티에서 403이 떴을때 이 메세지를 얻어와라 -->																		
																				
	<h2><c:out value="${errorMessage}"/></h2>									<!-- 출력 메세지 : 접근 거부 -->
																				<!-- CommonController에서 errorMessage라는 이름으로 바인딩 했기 떄문 -->

</body>
</html>