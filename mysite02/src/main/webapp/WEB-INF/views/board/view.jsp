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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${vo.content }</div>
						</td>
					</tr>
				</table>

				<!-- 댓글 
				<form method="post" action="${pageContext.request.contextPath }/board">
					<table class="tbl-ex">
						<tr>
							<td class="label">댓글</td>
							<td>
								<input type="text" name="title" value="" style="width: 400px;">
								<input type="submit" value="등록">
							</td>

						</tr>
					</table>
				</form>
				-->
				<div class="bottom">
					<c:if test="${not empty authUser }">
					<a href="${pageContext.request.contextPath }/board?a=commentform&no=${vo.no }&groupNo=${vo.groupNo}&depth=${vo.depth }">댓글</a>
					</c:if>
					<a href="${pageContext.request.contextPath }/board">글목록</a>
					<c:if test="${authUser.no == vo.userNo}">
						<a href="${pageContext.request.contextPath }/board?a=modifyform&no=${vo.no }">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>