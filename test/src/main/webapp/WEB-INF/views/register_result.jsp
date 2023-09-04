<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원 가입</title>
  <link rel="stylesheet" href="${path}/resources/css/register.css">
</head>
<body>
  <form id="signup">
    <fieldset>
      <legend>로그인 정보</legend>
      <ul>
        <li>
          <label for="userid" >아이디:${r.USERID}</label>       
        </li>
        
        <li>
        <br><label for="pwd1" >비밀번호: ${r.USERPW}</label>  
        </li>
        
        <li>
          <br><label for="level">회원 등급: ${r.GRADE}</label> 
        </li>
      </ul>
    </fieldset>
    <fieldset>
      <legend>개인 정보</legend>
      <ul>
        <li>
          <br><label for="fullname">이름: ${r.USERNAME}</label>    
        </li>
        
        <li>
          <br><label for="email">메일 주소: ${r.EMAIL}</label> 
        </li>
        
        <li>
          <br><label for="tel">연락처: ${r.PHONE}</label>      
        </li>  
      </ul>
    </fieldset>
  </form>
</body>
</html>
