<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<%@ include file="../includes_join/header.jsp" %>
	<script>
		$(document).ready(function(){
			var operForm = $("#operForm");
			
			// 수정 버튼 클릭 시 form action 속성값 변경 후 submit
			$("button[data-oper='modify']").on("click", function(e){
				operForm.attr("action", "/join/modify").submit();
			});
			
			// 목록 버튼 클릭 시 <input type="hidden"> 태그 제거 후 action을 변경한 후 submit
			$("button[data-oper='list']").on("click", function(e){
				operForm.find("#id").remove();
				operForm.attr("action", "/join/list")
				operForm.submit();
			});
		});
	</script>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Join Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Join Read Page</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div class="form-group">
						 		<label>ID</label>
						 		<input class="form-control" name="id"
						 			value="<c:out value='${join.id }' />" 
						 			readonly="readonly">
						 	</div>    		
							<div class="form-group">
								<label>Passwd</label>
							 	<input class="form-control" name="passwd"
						 			value="<c:out value='${join.passwd }' />" 
						 			readonly="readonly">
							</div>                           
							<div class="form-group">
							 	<label>Name</label>
							 	<input class="form-control" name="name"
						 			value="<c:out value='${join.name }' />" 
						 			readonly="readonly">
							</div>
							<div class="form-group">
							 	<label>Addr</label>
							 	<input class="form-control" name="addr"
						 			value="<c:out value='${join.addr }' />" 
						 			readonly="readonly">
							</div>
							<button data-oper="modify" class="btn btn-default">Modify</button>
							<button data-oper='list' class="btn btn-info">List</button>
                            
                            <form id="operForm" action="/join/modify" method="get">
                            	<input type="hidden" id="id" name="id" 
                            		value="<c:out value='${join.id }' />">
                            </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
       <%@ include file="../includes_join/footer.jsp" %> 
       