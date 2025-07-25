<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
	<h3>LOGIN</h3>
	<hr/>
	<c:if test="${not empty error }">
		<h3>error : ${error }</h3>
	</c:if>
	<c:if test="${not empty logout }">
		<h3>logout : ${logout }</h3>
	</c:if>
	<hr/>
	
	<form action="/login" method="post">
		id : <input type="text" name="username"/><br/>
		pw : <input type="text" name="password"/><br/>
		<input type="checkbox" name="remember-me"/>Remember Me<br/>
		<input type="submit" value="로그인"/>
		<sec:csrfInput/>
	</form>
</body>
</html>