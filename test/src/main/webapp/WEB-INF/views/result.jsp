<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>end</title>
<style>
			#container { 
				width:520px;
				border:1px solid black;
				padding:20px 40px;
				margin:0 auto;
			}
      fieldset { margin-bottom:15px; }
      legend { font-weight:bold; }
			ul {list-style-type: none;}
			li { line-height:30px;}
    </style>
</head>
<body>
<div id="container">
			<h1>프런트엔드 개발자 지원서 </h1>
			<p>HTML, CSS, Javascript에 대한 기술적 이해와 경험이 있는 분을 찾습니다.</p>
			<hr>
				<h4>개인정보</h4>
				<ul>
					 <li>
						이름: ${front.name }
					 </li>
					 <li>
					 	연락처: ${front.tel }
					 </li>
				</ul>
				<h4>지원 분야</h4>
					
				<ul>
					<li>
						<c:choose>
								<c:when test="${front.field == 'an' }">
									웹 퍼블리싱
								</c:when>
								
								<c:when test="${front.field == 'pd' }">
									웹 애플리케이션 개발
								</c:when>
								
								<c:when test="${front.field == 'eng' }">
									개발환경 개선
								</c:when>
								
								<c:otherwise>
									선택안함
								</c:otherwise>
					</c:choose>
					</li>
				</ul>
        <label>
          <h4>지원동기</h4>
         		${front.motive}
        </label>
			
		</div>
</body>
</html>