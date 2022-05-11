<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  <!-- 앞으로 sec 태그를 사용해주어야 함!! -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>admin 주소</h1>
	
	<h2>다양한 페이지 정보</h2>
	
	<!-- ★ principal =  CustomUserDetailsService의 loadUserByUsername()에서 리턴한 vo 정보임
	 -->
	
		<p>principal : <sec:authentication property="principal"/></p>							<!-- 전체적인 총괄 데이터 -->
		<p>MemberVO : <sec:authentication property="principal.member"/></p>						<!-- 실제 우리가 쓰는 데이터(VO 형태로 나옴) -->
		<p>사용자의 이름 : <sec:authentication property="principal.member.userName"/></p>			<!-- customUser에서 private MemberVO member로 만들었기 때문에 .member로 받아오는 것 -->
		<p>사용자의 아이디 : <sec:authentication property="principal.member.userId"/></p>			
		<p>사용자의 권한 목록 : <sec:authentication property="principal.member.authList"/></p>
		
		<hr/>	
		
		<script>
			let id = "<sec:authentication property="principal.member.userId"/>";
			console.log(id);
		</script>	
	
	
	<!-- ■ 로그인 한(인증된) 사용자인 경우 -->
		<sec:authorize access="isAuthenticated()">
			<a href="/customLogout">로그아웃</a>
		</sec:authorize>
	
	<a href="/customLogout">로그아웃 페이지 이동</a><!-- 얘는 get 방식으로 단순히 로그아웃 페이지로 이동한다 -->
	
</body>
</html>