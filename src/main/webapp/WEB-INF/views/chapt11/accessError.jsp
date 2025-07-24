<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Security Access Denied</title>
</head>
<body>
	<h3>Access Denied</h3>
	<h5>SPRING SECURITY MSG : ${SPRING_SECURITY_403_EXCEPTION.message }</h5>
	<h5>SERVER MSG : ${msg }</h5>
</body>
</html>