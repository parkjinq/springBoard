<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
	<c:when test="${commentsVOs.size()== 0}">
		<tr>
			<th>댓글</th>
			<td colspan="2">댓글 없음</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach begin="0" end="${commentsVOs.size()-1}" varStatus="status">
			<c:choose>
				<c:when test="${commentsVOs[status.index].cm_del eq 'N'}">
					<tr>
						<th>${status.index == 0 ? "댓글" : "" }</th>
						<td>${commentsVOs[status.index].cm_cnt }</td>
						<th>[${commentsVOs[status.index].userid }/<fmt:formatDate
								value="${commentsVOs[status.index].cm_date}"
								pattern="yyyy-MM-dd" />] <c:if
								test="${commentsVOs[status.index].userid eq loginUserVO.userId}">
								<input type="button" id="commentsDelete" value="삭제">
							</c:if> <input type="hidden" id="d_cmId"
							value="${commentsVOs[status.index].cm_id}">
						</th>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<th>${status.index == 0 ? "댓글" : "" }</th>
						<td colspan="2">삭제된 댓글 입니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</c:otherwise>
</c:choose>
<tr>
	<td></td>
	<td colspan="2"><input type="text" id="c_cm_cnt" name="c_cm_cnt"
		style="width: 500px"> <input type="button" id="insertCm"
		class="btn btn-default" value="댓글저장"></td>
</tr>
