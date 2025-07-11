<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>FORMHOME</h1>
	<hr/>
	
	<form action="/chapt03/http/register">
		<input type="submit" value="register(GET)"/>
	</form>
	<form action="/chapt03/http/register" method="post">
		<input type="submit" value="register(POST)"/>
	</form>
	<form action="/chapt03/http/modify">
		<input type="submit" value="modify(GET)"/>
	</form>
	<form action="/chapt03/http/modify" method="post">
		<input type="submit" value="modify(POST)"/>
	</form>
	<form action="/chapt03/http/remove" method="post">
		<input type="submit" value="remove(POST)"/>
	</form>
	<form action="/chapt03/http/list">
		<input type="submit" value="list(GET)"/>
	</form>
</body>
</html>