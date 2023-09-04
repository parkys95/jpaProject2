<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form action = "/sayHello2" method="get">
	    <fieldset>
	    	<label>아이디: <input type="text"  name="id" size="10"></label>
	      	<label>비밀번호: <input type="text"  name="passwd" size="10"></label>
	      	<input type="submit" value="로그인">
		</fieldset>
  	</form>
</body>
</html>