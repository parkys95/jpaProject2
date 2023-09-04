<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 양식</title>
</head>
<body>
	<h3>회원 가입</h3>
	<form action="/join/register" name="member" method="post">
		<p> 아이디 : <input type="text" name="id">
	    </p>
	    <p> 비밀번호 : <input type="password" name="passwd"></p>
	    <p> 이름 : <input type="text" name="name"></p>
	    <p> 주소 : <input type="text" name="addr"></p>
	    <p>
	    	<input type="submit" value="가입하기">
	    	<input type="reset" value="다시 쓰기">
	    </p>
	</form>	
</body>
</html>
