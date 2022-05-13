<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>member 주소</h1>
	
		<p>principal : <sec:authentication property="principal"/></p>							<!-- 전체적인 총괄 데이터 -->
		<p>MemberVO : <sec:authentication property="principal.member"/></p>						<!-- 실제 우리가 쓰는 데이터(VO 형태로 나옴) -->
		<p>사용자의 이름 : <sec:authentication property="principal.member.userName"/></p>			<!-- customUser에서 private MemberVO member로 만들었기 때문에 .member로 받아오는 것 -->
		<p>사용자의 아이디 : <sec:authentication property="principal.member.userId"/></p>			
		<p>사용자의 권한 목록 : <sec:authentication property="principal.member.authList"/></p>
	
	
	<!-- ■ 관리자만 볼 수 있는 '관리자 이동' 링크 추가 -->
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<a href="/secu/admin">관리자 페이지로 이동</a>
		</sec:authorize>
		
	<!-- ■ 로그인 한(인증된) 사용자인 경우 -->
		<sec:authorize access="isAuthenticated()"><!-- isAuthenticated()가 필요 없음. 왜냐면 이 페이지에 들어오는게 이미 권한이 있는 상태이니까 -->
			<a href="/customLogout">로그아웃</a>
		</sec:authorize>
			


</body>
</html>