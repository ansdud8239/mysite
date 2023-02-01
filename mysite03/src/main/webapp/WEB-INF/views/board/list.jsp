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
				<form id="search_form" action="${pageContext.request.contextPath }/board/" method="post">
					<input type="text" id="kwd" name="k" value="${kwd }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="totalcount" value="${paging.totalCount}" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${totalcount-status.index-(paging.page-1)*5}</td>
							<td style="text-align: left; padding-left:${vo.depth*15}px ">
								<c:if test="${vo.depth>0 }">
									<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								</c:if>
								<c:choose>
									<c:when test="${vo.status =='D' }">
										<del id="del">${vo.title }</del>
									</c:when>
									<c:when test="${vo.status =='U' }">
										<a href="${pageContext.request.contextPath }/board/view?n=${vo.no}&h=${vo.hit }&p=${paging.page }">${vo.title }</a> [수정]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath }/board/view?n=${vo.no}&h=${vo.hit }&p=${paging.page }">${vo.title }</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test="${vo.userNo==authUser.no }">
									<c:if test="${vo.status !='D' }">
										<a href="${pageContext.request.contextPath }/board/delete?n=${vo.no }&p=${paging.page}" class="del">삭제</a>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${paging.prev == true}">
								<li><a href="${pageContext.request.contextPath }/board/?p=${paging.page-1}&k=${kwd }">◀</a></li>
							</c:when>
							<c:otherwise>
								<li>◀</li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="${paging.beginPage }" end="${paging.endPage }" step="1" var="index">
							<c:choose>
								<c:when test="${paging.page == index }">
									<li class="selected">${index }</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/board/?p=${index }&k=${kwd }">${index }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${paging.next == true}">
								<li><a href="${pageContext.request.contextPath }/board/?p=${paging.beginPage+paging.displayRow }&k=${kwd }">▶</a></li>
							</c:when>
							<c:otherwise>
								<li>▶</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- pager 추가 -->
				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>