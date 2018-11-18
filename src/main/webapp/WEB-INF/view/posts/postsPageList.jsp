<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
.userClick {
	cursor: pointer;
}
</style>
<script>
	$(document).ready(function() {

		$("#postsPageList").on("click", ".postsClick", function() {
			console.log("postsClick");
			// 			var ps_id = $(this).children()[0].innerText;
			// 			var ps_id = $(this).children().eq(0).text();
			var ps_id = $(this).children().eq(0).find('input').val();
			console.log("ps_id : " + ps_id);

			$("#ps_id").val(ps_id);
			$("#frm").submit();
		});
		
		getPostsPageList(1, "${resultMap.boardVO.bd_id}");
	});

	function getPostsPageList(page, bd_id) {
		var pageSize = 10;
		var searchBox = $("#postsPageList").find("#searchBox").val();
		var searchInput = $("#postsPageList").find("#searchInput").val();

		$.ajax({
			url : "/posts/postsPageListAjax",
			type : "get",
			data : "page=" + page + "&pageSize=" + pageSize + "&bd_id="	+ bd_id + "&searchBox="	+ searchBox + "&searchInput=" + searchInput,
			success : function(dt) {
				$("#postsPageList").html(dt);
			},
			error : function(e) {
				alert("서버 오류 발생 다시 시도해주세요");
			}
		});
	}
</script>

<form id="frm" action="/posts/postsDetail" method="get">
	<input type="hidden" id="ps_id" name="ps_id">
</form>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div id="postsPageList">
		
	</div>
	
</div>
