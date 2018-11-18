<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$('document').ready(
			function() {
				// 		$("#createBoard").on("click", function() {
				// 			//생성할 게시판 제목
				// 			var bd_title = $("#c_bd_title").val();
				// 			//생성할 게시판 사용여부
				// 			var bd_use = $("#c_bd_use option:selected").val();

				// 			console.log("bd_title : " + bd_title + "/ bd_use : " + bd_use);

				// 		});

				$(".updateBoard").on(
						"click",
						function() {
							var tr = $(this).parent().parent();
							var td = tr.children();

							//수정할 게시판 이름 값
							var bd_title = td.eq(0).children().val();
							if(bd_title == "" || bd_title == null){
								alert("수정할 게시판 타이틀을 입력해주세요");
							} else{
								//수정할 게시판 ID 값
								var bd_id = td.eq(0).children().eq(1).val();
								//수정할 게시판 사용여부 값
								var id = document.getElementById(bd_id);
								var bd_use = id.options[id.selectedIndex].value;
	
								location.href = "/board/boardUpdate?bd_id=" + bd_id
										+ "&bd_title=" + bd_title + "&bd_use="
										+ bd_use;
							}
						});
				$("#createBoard").on("click", function() {
					var bd_title = $("#c_bd_title").val();
					if(bd_title == "" || bd_title == null){
						alert("생성할 게시판의 타이틀을 입력해주세요.");
					} else {
						$("#bd_insert").submit();
					}
				});
			});
</script>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

	<div class="row">
		<div class="col-sm-8 blog-main">
			<h2 class="sub-header">게시판 리스트</h2>
			<div class="table-responsive">

				<table class="table table-striped table-hover">
					<tr>
						<th>게시판 이름</th>
						<th>사용여부</th>
						<th>생성/수정</th>
					</tr>
					<form id="bd_insert" action="/board/boardInsert" method="post">
					<tr style="background-color: powderblue">
						<td><input type="text" id="c_bd_title" name="bd_title"
							value="" /><input type="hidden" id="userid" name="userid" value="${loginUserVO.userId}"></td>
						<td><select id="c_bd_use" name="bd_use">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
						</select></td>
						<td><input type="button" id="createBoard" value="생성"></td>
					</tr>
					</form>

					<c:forEach items="${boardVOs}" var="board">
						<tr>
							<td><input type="text" name="${board.bd_id }"
								value="${board.bd_title}" /> <input type="hidden"
								value="${board.bd_id }"></td>
							<td><select id="${board.bd_id }" name="bd_use">
									<c:choose>
										<c:when test="${board.bd_use == 'Y'}">
											<option value="Y" selected="selected">사용</option>
											<option value="N">미사용</option>
										</c:when>
										<c:otherwise>
											<option value="Y">사용</option>
											<option value="N" selected="selected">미사용</option>
										</c:otherwise>
									</c:choose>
							</select></td>
							<td><input type="button" class="updateBoard" value="수정"></td>
						</tr>
					</c:forEach>
				</table>

			</div>
			<div class="text-center"></div>
		</div>
	</div>
</div>
