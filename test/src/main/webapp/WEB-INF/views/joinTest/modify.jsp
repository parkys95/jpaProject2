<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<%@ include file="../includes_join/header.jsp" %>
	<script>
		$(document).ready(function(){
			var formObj = $("form");
			
			$("button").on("click", function(e){
				// <form>태그의 모든 버튼은 기본적으로 submit으로 처리하기 때문에 기본 동작(submit)을 막음
				e.preventDefault();
				
				var operation = $(this).data("oper");
				
				console.log(operation);
				
				if(operation === "remove"){
					if(confirm("정말 삭제하시겠습니까?")) {
						formObj.attr("action", "/join/remove");
					}else {
						return;
					}
				} else if(operation === "list"){
					// list로 이동
					self.location = "/join/list";
					return;
				}
				formObj.submit();
			});
		});
	</script>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Join Modify Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Join Modify Page</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form role="form" action="/join/modify" method="post">
                        		<div class="form-group">
							 		<label>ID</label>
							 		<input class="form-control" name="id"
							 			value="<c:out value='${join.id }' />" 
							 			readonly="readonly">
							 	</div>    		
								<div class="form-group">
									<label>Passwd</label>
								 	<input class="form-control" name="passwd"
							 			value="<c:out value='${join.passwd }' />">
								</div>                           
								<div class="form-group">
								 	<label>Name</label>
								 	<input class="form-control" name="name"
							 			value="<c:out value='${join.name }' />">
								</div>
								<div class="form-group">
								 	<label>Addr</label>
								 	<input class="form-control" name="addr"
							 			value="<c:out value='${join.addr }' />">
								</div>	
                        	</form>
                        
                        	<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>	
							<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
							<button data-oper='list' class="btn btn-info"
								onclick="location.href='/join/list'">List
							</button>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
       <%@ include file="../includes_join/footer.jsp" %> 
       