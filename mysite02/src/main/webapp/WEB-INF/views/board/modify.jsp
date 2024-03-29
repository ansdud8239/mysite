<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/hearder.jsp" />
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board">
				<input type="hidden" name="a" value="modify">
				<input type="hidden" name="no" value="${vo.no }">
				<input type="hidden" name="pageNum" value="${pageNum }">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${vo.title }"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content">${vo.content }</textarea></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=viewform&no=${vo.no}&hit=${vo.hit }&pageNum=${pageNum }">취소</a>
						<input type="submit" value="수정">
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>