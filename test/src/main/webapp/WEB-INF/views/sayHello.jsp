<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sayHello</title>
</head>
<body>
	<p>아이디: ${id}</p>
	<p>비밀번호: ${passwd}</p>
	<p>modelAttribute의 아이디: ${joinVO.id }</p>
	<p>modelAttribute의 비밀번호: ${joinVO.passwd }</p>
</body>
</html>