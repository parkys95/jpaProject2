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
  <form id="signup" action="/register_result" method="get">
    <fieldset>
      <legend>로그인 정보</legend>
      <ul>
        <li>
          <label for="userid">아이디</label>
          <input id="userid" name="USERID" type="text" autofocus>
        </li>
        <li>
          <label for="pwd1">비밀번호</label>
          <input id="pwd1" name="USERPW" type="password">
        </li>
        <li>
          <label for="pwd2">비밀번호 확인</label>
          <input id="pwd2" name="USERPW" type="password">
        </li>  
        <li>
          <label for="level">회원 등급</label>
          <input id="level" name="GRADE" type="text" value="준회원">
        </li>
      </ul>
    </fieldset>
    <fieldset>
      <legend>개인 정보</legend>
      <ul>
        <li>
          <label for="fullname">이름</label>
          <input id="fullname" name="USERNAME" type="text" placeholder="5자미만 공백없이">
        </li>
        <li>
          <label for="email">메일 주소</label>
          <input id="email" name="EMAIL" type="email" placeholder="abcd@domain.com" autocomplete="off">
        </li>
        <li>
          <label for="tel">연락처</label>
          <input id="tel" name="PHONE" type="tel" autocomplete="off">
        </li>  
      </ul>
    </fieldset>
    <fieldset>
      <button type="submit"> 제출 </button> 
    </fieldset>
  </form>
</body>
</html>
