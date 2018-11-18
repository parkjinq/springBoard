<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${resultMap.boardVO.bd_title}</h2>
		<div class="table-responsive">
			<table class="table table-striped table-hover">
				<tr>
					<th>게시글번호</th>
					<th>제목</th>
					<th>작성자아이디</th>
					<th>작성일시</th>
				</tr>

				<c:forEach items="${resultMap.postsVOs}" var="ps">
					<c:choose>
						<c:when test="${ps.ps_del eq 'N'}">
							<tr class="postsClick">
								<td>${ps.rnum}<input type="hidden" value="${ps.ps_id}">
								</td>
								<td>${ps.ps_title}</td>
								<td>${ps.userid}</td>
								<td><fmt:formatDate value="${ps.ps_date}"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>${ps.rnum}</td>
								<th>삭제된 게시글입니다.</th>
								<td colspan="2"></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</div>

		<a class="btn btn-default pull-right"
			href="/posts/postsInsertView_new?bd_id=${resultMap.boardVO.bd_id}&ps_id2=">새글
			등록</a>

		<div class="text-center">
			<ul class="pagination">
				<li><a
					href="javascript:getPostsPageList(1,'${resultMap.boardVO.bd_id}')"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a
					href="javascript:getPostsPageList(1,'${resultMap.boardVO.bd_id}')"><<</a></li>

				<c:set var="startIndex" value="0"></c:set>
				<c:set var="endIndex" value="0"></c:set>
				<c:set var="pagingNum" value="0"></c:set>
				<c:set var="pageAllNum" value="${resultMap.pageCnt}"></c:set>

				<c:choose>
					<c:when test="${resultMap.pageCnt != 0}">
						<c:forEach begin="0" end="${(resultMap.pageVO.page -1)/10}"
							varStatus="status">
							<c:set var="startIndex" value="${(status.index * 10) + 1 }"></c:set>
							<c:set var="endIndex" value="${startIndex + 9 }"></c:set>
						</c:forEach>
						<c:if test="${endIndex > pageAllNum }">
							<c:set var="endIndex" value="${pageAllNum }"></c:set>
						</c:if>

					</c:when>
					<c:otherwise>
						<c:set var="startIndex" value="1"></c:set>
						<c:set var="endIndex" value="1"></c:set>
					</c:otherwise>
				</c:choose>
				<li><a
					href="javascript:getPostsPageList(${startIndex == 1 ? 1 : startIndex-10},'${resultMap.boardVO.bd_id}')"><</a></li>

				<c:forEach begin="${startIndex }" end="${endIndex }"
					varStatus="status">
					<li><a
						href="javascript:getPostsPageList(${status.index },'${resultMap.boardVO.bd_id}')">${status.index }</a></li>
				</c:forEach>

				<li><a
					href="javascript:getPostsPageList(${startIndex + 5 > pageAllNum ? startIndex : startIndex + 10},'${resultMap.boardVO.bd_id}')">></a></li>
				<li><a
					href="javascript:getPostsPageList(${pageAllNum },'${resultMap.boardVO.bd_id}')">>></a></li>
				<li><a
					href="javascript:getPostsPageList(${pageAllNum },'${resultMap.boardVO.bd_id}')"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</div>
		<form
			action="javascript:getPostsPageList(1,'${resultMap.boardVO.bd_id}')"
			method="get">

			<div id="searchPosts">
				<select id="searchBox">
					<option value="id">작성자</option>
					<option value="title">제목</option>
				</select> <input type="text" id="searchInput" name="searchInput"><input
					type="submit" value="검색">
			</div>
			<script type="text/javascript">
				$("#searchBox").val('${resultMap.searchBox}');
				$("#searchInput").val('${resultMap.searchInput}');
			</script>
		</form>
	</div>
</div>