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
	<div id="navigation">
		<ul>
			<li><a href="${pageContext.request.contextPath }/ ">home</a></li>
			<li><a href="${pageContext.request.contextPath }/guestbook/">방명록</a></li>
			<li><a href="${pageContext.request.contextPath }/guestbook/spa">방명록(SPA)</a></li>

			<li><a href="${pageContext.request.contextPath }/board/">게시판</a></li>
			<li><a href="${pageContext.request.contextPath }/gallery/">갤러리</a></li>
		</ul>
	</div>
</body>
</html>