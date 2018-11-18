<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
.kyuseung {
	width: 200px;
	height: 200px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		var ev = "click";

		$(".postsDelete").on(ev, function() {
			console.log("posts-delete_click");
			$("#frm").submit();
		});

		$("#commentsTable").on(ev, "#insertCm", function() {
			console.log("comments_insert_click");
			var cm_cnt = $("#c_cm_cnt").val();
			if(cm_cnt == "" || cm_cnt == null){
				alert("댓글 내용을 입력해주세요");
			} else {
				$("#ci_cm_cnt").val(cm_cnt);
				commentsInsert();
			}
			
		});

		$("#commentsTable").on(ev, "#commentsDelete", function() {
			console.log("comments_delete_click");
			var cm_id = $(this).parent().children().eq(1).val();
			$("#cm_id").val(cm_id);
			commentsDelete();
		});

		$("#replyPosts").on(ev, function() {
			console.log("reply_insert_click");
			$("#frm2").submit();
		});

		commentsFirst();
	});

	function commentsFirst() {
		$.ajax({
			url : "/comments/commentsFirst",
			type : "get",
			data : "ps_id=" + "${postsVO.ps_id}",
			success : function(dt) {
				$("#commentsTable").html(dt);
			},
			error : function(e) {
				alert("서버 오류 발생 다시 시도해주세요");
			}
		});
	}

	function commentsInsert() {
		$.ajax({
			url : "/comments/commentsInsert",
			type : "post",
			data : $("#cmIns").serialize(),
			success : function(dt) {
				$("#commentsTable").html(dt);
			},
			error : function(e) {
				alert("서버 오류 발생 다시 시도해주세요");
			}
		});
	}

	function commentsDelete() {
		$.ajax({
			url : "/comments/commentsDelete",
			type : "post",
			data : $("#cmDel").serialize(),
			success : function(dt) {
				$("#commentsTable").html(dt);
			},
			error : function(e) {
				alert("서버 오류 발생 다시 시도해주세요");
			}
		});
	}
</script>

<form id="frm" action="/posts/postsDelete" method="post">
	<input type="hidden" id="ps_id" name="ps_id" value="${postsVO.ps_id}">
	<input type="hidden" id="bd_id" name="bd_id" value="${postsVO.bd_id}">
</form>
<form id="frm2" action="/posts/postsInsertView_new" method="get">
	<input type="hidden" id="bd_id" name="bd_id" value="${postsVO.bd_id}">
	<input type="hidden" name="ps_id2" value="${postsVO.ps_id}">
</form>

<form class="form-horizontal" role="form" action="/posts/postsUpdateView"
	method="get">
	<div class="form-group">
		<input type="hidden" name="ps_id" value="${postsVO.ps_id}">
		<table class="table table-striped">
			<tr>
				<th>제목</th>
				<td colspan="2"><span>${postsVO.ps_title }</span></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td colspan="2">
					<p>${postsVO.ps_cnt }</p>
				</td>
			</tr>
			<c:choose>
				<c:when test="${attachmentsVOs.size() == 0}">
					<tr>
						<th>첨부파일</th>
						<td colspan="2">첨부파일 없음</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach begin="0" end="${attachmentsVOs.size()-1}"
						varStatus="status">
						<tr>
							<th>${status.index == 0 ? "첨부파일" : ""}</th>
							<td colspan="2"><a
								href="/attachments/attFileDown?att_path=${attachmentsVOs[status.index].att_path}">${attachmentsVOs[status.index].att_path == null ? "" : attachmentsVOs[status.index].att_originname}</a></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:if test="${postsVO.userid eq loginUserVO.userId}">
					<button type="submit" class="btn btn-default">수정</button>
					<input type="button" class="btn btn-default postsDelete" value="삭제">
				</c:if>
				<input type="button" id="replyPosts" class="btn btn-default"
					value="답글">
			</div>
		</div>
	</div>
	<table class="table table-striped" id="commentsTable">

	</table>
</form>
<form id="cmDel" action="comments/commentsDelete" method="post">
	<input type="hidden" id="d_ps_id" name="ps_id"
		value="${postsVO.ps_id}"> <input type="hidden" id="cm_id"
		name="cm_id">
</form>

<form id="cmIns" action="/comments/commentsInsert" method="post"
	class="form-horizontal">
	<input type="hidden" id="ci_cm_cnt" name="cm_cnt"> <input
		type="hidden" id="ci_ps_id" name="ps_id" value="${postsVO.ps_id}">
	<input type="hidden" id="ci_userId" name="userid"
		value="${loginUserVO.userId}">
</form>


