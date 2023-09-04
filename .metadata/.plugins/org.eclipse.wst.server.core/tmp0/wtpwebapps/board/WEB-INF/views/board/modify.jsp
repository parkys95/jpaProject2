<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<%@include file="../includes/header.jsp" %>

<script>
	$(document).ready(function(){
		var formObj = $("form");
		
		$("button").on("click", function(e){
			// submit 이벤트 막기
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === "remove") {
				if(confirm("정말 삭제하시겠습니까?")) {
					formObj.attr("action", "/board/remove");	
				} else {
					return;
				}
			} else if(operation === "list") {
				formObj.attr("action", "/board/list").attr("method", "get");
				formObj.empty();
			}
			formObj.submit();
		});
	});
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify Page</div>
			<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
				<div class="form-group">
					<label>Bno</label> 
					<input class="form-control" name="bno" value="${board.bno }" readonly>
				</div>				
				<div class="form-group">
					<label>Title</label> 
					<input class="form-control" name="title" value="${board.title }">
				</div>
				<div class="form-group">
					<label>Content</label> 
					<input class="form-control" name="content" value="${board.content }">
				</div>
				<div class="form-group">
					<label>Writer</label> 
					<input class="form-control" name="writer" value="${board.writer }">
				</div>
				<div class="form-group">
					<label>RegDate</label> 
					<input class="form-control" name="regDate" 
						value="<fmt:formatDate pattern='yyyy/MM/dd' value="${board.regdate }"/>"
						readonly>
				</div>
				<div class="form-group">
					<label>Update Date</label> 
					<input class="form-control" name="updateDate" 
						value="<fmt:formatDate pattern='yyyy/MM/dd' value="${board.updateDate }"/>"
						readonly>
				</div>				
				<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
				<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
				<button type="submit" data-oper="list" class="btn btn-info">List</button>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../includes/footer.jsp" %>
</div>








