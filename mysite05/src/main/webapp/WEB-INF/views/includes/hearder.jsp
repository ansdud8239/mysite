<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<h1>${site.title }</h1>
		<ul>
			<c:choose>
				<c:when test="${empty authUser}">
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
					<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/user/update">회원정보수정</a></li>
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
					<li>${authUser.name }님 안녕하세요 ^^;</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>