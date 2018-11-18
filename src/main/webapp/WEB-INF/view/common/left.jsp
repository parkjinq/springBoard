<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="/main">Main <span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="/board/boardManage">게시판 생성</a></li>

		<c:forEach items="${boardVOs}" var="board">
			<c:if test="${board.bd_use eq 'Y'}">
				<li class="active"><a
					href="/posts/postsPageList?page=1&pageSize=10&bd_id=${board.bd_id }">${board.bd_title }</a></li>
			</c:if>
		</c:forEach>

	</ul>
</div>
