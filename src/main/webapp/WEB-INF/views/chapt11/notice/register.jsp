<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Security Notice Register</h1>
	<hr/>
	
	<sec:authentication property="principal" var="princ"/>
	<sec:authentication property="principal.member" var="member"/>
	<sec:authentication property="principal.member.authList" var="authList"/>
	
	<p>
		아이디 : ${member.userId }<br/>
		비밀번호 : ${member.userPw }<br/>
		이름 : ${member.userName }<br/>
		---------------------------<br/>
	</p>
	
	<p>principal : ${princ }</p>
	<p>member : ${member}</p>
	<p>
		authList-----------------<br/>
		<c:forEach items="${authList }" var="auth" varStatus = "vs">
			역할${vs.count } : ${auth.auth }<br/>
		</c:forEach>
	</p>
	
	<p>
		조건에 따른 역할명 --------------<br/>
		<sec:authorize access="hasRole('ROLE_MEMBER')">
			- 역할명은 회원입니다!<br/>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			- 역할명은 관리자입니다!<br/>
		</sec:authorize>
	</p>
	
	<a href="/security/notice/list">List</a>
</body>
</html>