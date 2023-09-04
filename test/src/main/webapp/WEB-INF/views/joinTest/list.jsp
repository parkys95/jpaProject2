<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<%@ include file="../includes_join/header.jsp" %>
<script>
	$(document).ready(function(){
		// 회원 등록 버튼 클릭 이벤트
		$("#regBtn").on("click", function(){
			self.location = "/join/register";
		});
	});
</script>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Join List Page
                            <button id="regBtn" type="button" class="btn btn-xs pull-right">
                            	Register New Member</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>아이디</th>
                                        <th>비밀번호</th>
                                        <th>이름</th>
                                        <th>주소</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="join" items="${list }">
                                	<tr>
                                		<td><c:out value="${join.id }" /></td>
                                		<td><c:out value="${join.passwd }" /></td>
                                		<td>
                                			<a href="/join/get?id=<c:out value='${join.id }'/>">
                                				<c:out value="${join.name }" /></a>
                                		</td>
                                		<td><c:out value="${join.addr }" /></td>
                                	</tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
       <%@ include file="../includes_join/footer.jsp" %> 
       
       
       
       
       
       
       
       