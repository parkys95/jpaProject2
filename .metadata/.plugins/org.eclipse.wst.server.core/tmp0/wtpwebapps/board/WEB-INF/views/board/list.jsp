<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@include file="../includes/header.jsp" %>
	
<script>
	$(document).ready(function(){
		var result = '${result}';
		
		checkModal(result);
		
		// 앞뒤로 이동하였을 때 url만 변경되고, 해당 객체에 담긴 이벤트는 사라지게 함
		// replaceState(내용, 제목, 위치) 
		history.replaceState({}, null, null);
		
		function checkModal(result) {
			// result값이 빈값이거나
			// history 객체에 어떠한 값이 존재하면 아무 동작도 하지 않도록 return으로 종료시킴
			if(result === '' || history.state) {
				return;		
			}
			if(parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		//----------------------------------------------------------------------
		var actionForm = $("#actionForm");
		
		// 페이징 이동
		$(".paginate_button a").on("click", function(e){
			e.preventDefault();
			
			actionForm.attr("action", "/board/list");
			actionForm.find("input[name=pageNum]").val($(this).attr("href"));
			actionForm.submit();
		});
		
		// 상세 화면 이동
		$(".move").on("click", function(e){
			e.preventDefault();
			
			$("input[name=bno]").val($(this).attr("href"));
			actionForm.attr("action", "/board/get");
			actionForm.submit();
		});
		
		// 검색 부분--------------------------------------------------------------------------------------
		var searchForm = $("#searchForm");
		
		$("#searchForm button").on("click", function(e){
			if(searchForm.find("option:selected").val() == null) {
				alert("검색종류를 선택하세요");
				return false;
			}
			
			if(searchForm.find("input[name='keyword']").val() == null) {
				alert("키워드를 입력하세요");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			e.prevenDefault();
			
			searchForm.submit();
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
                            Board List Page
                            <button class="pull-right" onclick="location.href='/board/register'">Register</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list }" var="board">
                                <tr>
                                	<td>${board.bno }</td>
                                	<td>
                              			<a href="${board.bno }" class="move">${board.title }</a>  	
                                	</td>
                                	<td>${board.writer }</td>
                                	<td>
                                		<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                			value="${board.regdate }"/>
                                	</td>
                                	<td>
                                		<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                			value="${board.updateDate }"/>
                                	</td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
                            <!-- 검색 영역 추가 -->
                            <div class="row">
                           		<div class="col-lg-12">
                            		
                           			<form id="searchForm" action="/board/list" method="get">
                            			<select name="type">
                             			<option value=""
                             				<c:out value="${pageMaker.paging.type == null ? 'selected' : '' }"/>>--</option>
                             			<option value="T"
                             				<c:out value="${pageMaker.paging.type eq 'T' ? 'selected' : '' }"/>>제목</option>
                             			<option value="C"
                             				<c:out value="${pageMaker.paging.type eq 'C' ? 'selected' : '' }"/>>내용</option>
                             			<option value="W"
                             				<c:out value="${pageMaker.paging.type eq 'W' ? 'selected' : '' }"/>>작성자</option>
                             			<option value="TC"
                             				<c:out value="${pageMaker.paging.type eq 'TC' ? 'selected' : '' }"/>>제목 or 내용</option>
                             			<option value="TW"
                             				<c:out value="${pageMaker.paging.type eq 'TW' ? 'selected' : '' }"/>>제목 or 작성자</option>
                             			<option value="TCW"
                             				<c:out value="${pageMaker.paging.type eq 'TCW' ? 'selected' : '' }"/>>제목 or 내용 or 작성자</option>
                            			</select>
                            			<input type="text" name="keyword" value="${pageMaker.paging.keyword }">
                            			<input type="hidden" name="pageNum" value="${pageMaker.paging.pageNum }">
                            			<input type="hidden" name="amount" value="${pageMaker.paging.amount }">
                            			<button class="btn btn-primary">Search</button>
                            		</form>

                           		</div>
                            </div>
                            
                            <!-- paging 추가 -->
                            <div class="pull-right mt-5 mb-4">
                            	<ul class="pagination justify-content-center">
                             		<c:if test="${pageMaker.prev }">
                             		<li class="paginate_button page-item previous" id="dataTable_previous">
                             			<a href="${pageMaker.startPage - 1 }" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link fw-bold"> < </a>
                         			</li>
                         			</c:if>
                         			<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
                         			<li class="paginate_button page-item ${pageMaker.paging.pageNum == num ? "active" : "" }">
                         				<a href="${num }" aria-controls="dataTable" data-dt-idx="${num }" tabindex="0" class="page-link fw-bold">${num }</a>
                     				</li>
                     				</c:forEach>
                     				
                     				<c:if test="${pageMaker.next }">
                     				<li class="paginate_button page-item next" id="dataTable_next">
                     					<a href="${pageMaker.endPage + 1 }" aria-controls="dataTable" data-dt-idx="${pageMaker.endPage }" tabindex="0" class="page-link fw-bold"> > </a>
                  					</li>
                  				</c:if>
                 				</ul>
                            </div>
                            
                            <!-- 페이징 이동 form -->
                            <form id="actionForm" action="/board/list" method="get">
	                       		<input type="hidden" name="bno">
	                       		<input type="hidden" name="pageNum" value="${pageMaker.paging.pageNum }">
	                       		<input type="hidden" name="amount" value="${pageMaker.paging.amount }">
	                       		<input type="hidden" name="type" value="${pageMaker.paging.type }">
	                       		<input type="hidden" name="keyword" value="${pageMaker.paging.keyword }">
                            </form>
                            
                            
                            <!-- Modal 추가 -->
				            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				            	aria-labelledby="myModalLabel" aria-hidden="true">
				             	<div class="modal-dialog">
				             		<div class="modal-content">
				              		<div class="modal-header">
				              			<h4 class="modal-title" id="myModalLabel">Board Register</h4>
				              			<button type="button" class="close" data-dismiss="modal"
				              				aria-hidden="true">&times;</button>
				              		</div>
				              		<div class="modal-body">처리가 완료되었습니다.</div>
				              		<div class="modal-footer">
				              			<button type="button" class="btn btn-default" data-dismiss="modal">
				              			Close</button>
				              		</div>
				              	</div>
				              	<!--  /.modal-content -->
				             	</div>
				             	<!--  /.modal-dialog -->
				            </div>
				            <!--  /.modal -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

    <%@include file="../includes/footer.jsp" %>
    