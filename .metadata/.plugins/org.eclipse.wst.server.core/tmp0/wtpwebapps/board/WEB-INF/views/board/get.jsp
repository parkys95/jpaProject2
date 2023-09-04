<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     

<%@include file="../includes/header.jsp" %>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
	$(document).ready(function() {
		var operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e){
			operForm.attr("action", "/board/modify")
			operForm.submit();
		});
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list");
			operForm.submit();
		});
		
		// 댓글 리스트 조회
		var bnoValue = '${board.bno}';
		var replyUL = $(".chat");
		
		showList(1);
		
		function showList(page){
			console.log("show list " + page);
			
// 			replyService.getList({bno:bnoValue, page:page||1}, function(list){
			replyService.getList({bno:bnoValue, page:page||1}, function(replyCnt, list){	
				console.log("replyCnt: " + replyCnt);
				console.log("list: " + list);
				console.log(list);
				
				var str = "";
				if(list == null || list.length == 0){
					replyUL.html("");
					
					return;
				}
				for(var i = 0, len = list.length || 0; i < len; i++){
					str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
					str += "	<div><div class='header'>" + list[i].replyer;
					str += "		<small class='pull-right text-muted'>" + replyService.displayTime(list[i].regDate);
					str += "		</small>";
					str += "		</div>";
					str += "		<p><strong class='primary-font'>" + list[i].reply + "</strong></p>";
					str += "	</div>";
					str += "</li>";
				}
				replyUL.html(str);
				
				showReplyPage(replyCnt);
// 				showReplyPage();
			});
		}
		
		var pageNum = 1;
		var replyPageFooter = $(".panel-footer");
		
		function showReplyPage(replyCnt){
			var endNum = Math.ceil(pageNum / 5.0) * 5;
			var startNum = endNum - 4;
			
			var prev = startNum != 1;
			var next = false;
			
			if(endNum * 5 >= replyCnt){
				endNum = Math.ceil(replyCnt / 5.0);
			}
			
			if(endNum * 5 < replyCnt){
				next = true;
			}
			
			var str = "<ul class='pagination justify-content-center'>";
			
			if(prev){
				str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>";
			}
			
			for(var i = startNum; i <= endNum; i++){
				var active = pageNum == i ? "active" : "";
				
				str += "<li class='page-item " + active + " '><a class='page-link' href='" + i + "'>" + i + "</a></li>";
			}		
			
			if(next){
				str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>Next</a></li>"; 
			}
				
			str += "</ul></div>";
			
			console.log(str);
			console.log(replyPageFooter);
			console.log("prev " + replyPageFooter.html());
			replyPageFooter.html(str);
			console.log("next " + replyPageFooter.html());
		}
		
		// 페이지 번호 클릭 이벤트
		replyPageFooter.on("click", "li a", function(e){
			e.preventDefault();
			console.log("page click");
			
			var targetPageNum = $(this).attr("href");
			
			console.log("targetPageNum: " + targetPageNum);
			
			pageNum = targetPageNum;
			
			showList(pageNum);
		});
		
		// 댓글 쓰기 modal 창 열기
		var modal = $(".modal");
		var modalInputReply = modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputRegDate = modal.find("input[name='regDate']");
		
		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		
		$("#addReplyBtn").on("click", function(e){
			modal.find("input").val("");
			modalInputRegDate.closest("div").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();
			
			modalRegisterBtn.show();
			
			$(".modal").modal("show");
			
			console.log($(".modal-body").find("input[name='reply']").length);
		});
		
		// 새 댓글 입력
		modalRegisterBtn.on("click", function(e){
			var reply = {
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val(),
				bno : bnoValue
			};
			
			// 댓글 내용/작성자 validation check
			var replyArea = $("#myModal").find("input[name='reply']");
			var replyerArea = $("#myModal").find("input[name='replyer']");
			
			if(replyArea.val() == "" || replyArea.val().length < 1) {
				alert("내용을 입력해주세요.");
				replyArea.focus();
				return false;
			}
			
			if(replyerArea.val() == "" || replyerArea.val().length < 1) {
				alert("작성자를 입력해주세요.");
				replyerArea.focus();
				return false;
			}
			
			replyService.add(reply, function(result){
				alert(result);
				
				modal.find("input").val("");
				modal.modal("hide");
				
				// 댓글 리스트 새로고침
				showList(1);
			});
		});
		
		// 모달 내 close 버튼 클릭 이벤트
		$("#modalCloseBtn").on("click", function(){
			// 모달 창 닫기
			$(".modal").modal("hide");
		});
		
		// 댓글 항목 클릭 이벤트
		$(".chat").on("click", "li", function(e){
			var rno = $(this).data("rno");
			
			replyService.get(rno, function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputRegDate.val(replyService.displayTime(reply.regDate)).attr("readonly", "readonly");
				modal.data("rno", reply.rno);
				
				// closest -> 왼쪽 태그를 기준으로 가장 가까이에 있는 부모 태그 선택
				modalInputRegDate.closest("div").show();
				modal.find("button[id != 'modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				$(".modal").modal("show");
			});
		});	
		
		// 댓글 수정 버튼 클릭 이벤트
		modalModBtn.on("click", function(e){
			var reply = {rno:modal.data("rno"), reply:modalInputReply.val(),
						replyer:modalInputReplyer.val()};
			
			replyService.update(reply, function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		// 댓글 삭제 버튼 클릭 이벤트
		modalRemoveBtn.on("click", function(e){
			var rno = modal.data("rno");
			
			if(!confirm("정말 삭제하시겠습니까?")){
				return false;
			}
			
			replyService.remove(rno, '', function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
	});
	
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<div class="panel-body">
				<div class="form-group">
					<label>Bno</label> 
					<input class="form-control" name="bno" value="${board.bno }" readonly>
				</div>				
				<div class="form-group">
					<label>Title</label> 
					<input class="form-control" name="title" value="${board.title }" readonly>
				</div>
				<div class="form-group">
					<label>Content</label> 
					<input class="form-control" name="content" value="${board.content }" readonly>
				</div>
				<div class="form-group">
					<label>Writer</label> 
					<input class="form-control" name="writer" value="${board.writer }" readonly>
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
				<button data-oper="modify" class="btn btn-default"
					onclick="location.href='/board/modify?bno=${board.bno }'">Modify</button>
				<button data-oper="list" class="btn btn-info"				
					onclick="location.href='/board/list'">List</button>
			</div>
			<form id="operForm" action="/board/modify" method="get">
				<input type="hidden" id="bno" name="bno" value="${board.bno }"/>
				<input type="hidden" name="pageNum" value="${paging.pageNum }"/>
				<input type="hidden" name="amount" value="${paging.amount }"/>
				<input type="hidden" name="type" value="${paging.type }"/>
				<input type="hidden" name="keyword" value="${paging.keyword }"/>
			</form>
		</div>
	</div>
	
	<div class="row mt-xl-5">
		<div class="container-fluid">
			<div class="col-lg-12">
				<!-- /.panel -->
				<div class="panel panel-default">
					<i class="fa fa-comments fa-fw"></i> Reply
					<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right float-right">New Reply</button>
				</div>
				
				<!-- /.panel-heading -->
				<div class="panel-body">
					<ul class="chat">
						<!-- start reply -->
						<li class="left clearfix" data-rno="12">
							<div>
								<div class="header">
									<strong class="primary-font">user00</strong>
									<small class="pull-right text-muted">2018-01-01 13:13</small>
								</div>
								<p>Good job!</p>
							</div>		
						</li>
						<!-- end reply -->
					</ul>
					<!-- ./ end ul -->
				</div>
				<!-- /.panel .chat-panel -->
				<div class="panel-footer card-footer">
				
				</div>
			</div>
		</div>
		<!-- ./ end row -->
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		    <div class="modal-content">
		    	<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal"
			          aria-hidden="true">&times;</button>
			        <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
		      	</div>
		      	<div class="modal-body">
			        <div class="form-group">
			          <label>Reply</label> 
			          <input class="form-control" name='reply' value='New Reply!!!!'>
			        </div>      
			        <div class="form-group">
			          <label>Replyer</label> 
			          <input class="form-control" name='replyer' value='replyer'>
			        </div>
			        <div class="form-group">
			          <label>RegDate</label> 
			          <input class="form-control" name='regDate' value='2018-01-01 13:13'>
			        </div>
		      	</div>
				<div class="modal-footer">
			        <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
			        <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
			        <button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
			        <button id='modalCloseBtn' type="button" class="btn btn-primary">Close</button>
		      	</div>          
	      	</div>
	        <!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<%@include file="../includes/footer.jsp" %>
</div>








