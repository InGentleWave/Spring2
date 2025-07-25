<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>MAIN HOME</h3>
	<p>The time on the server is ${serverTime }</p>
	<hr/>
	
	<sec:authorize access="isAnonymous()">
		<a href="/login">로그인</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.member" var="member"/>
		<p><font color="red">${member.getUserId() }</font>님 환영합니다.</p>
		<a href="/logout">로그아웃</a>
	</sec:authorize>
	
	<h3>MAIN MENU</h3>
	<a href="/security/board/list">Board List</a><br/>
	<a href="/security/notice/list">Notice List</a>
</body>
</html>