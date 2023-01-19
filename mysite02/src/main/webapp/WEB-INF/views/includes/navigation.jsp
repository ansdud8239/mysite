<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="navigation">
		<ul>
			<li><a href="<%=path%>">home</a></li>
			<li><a href="<%=path%>/guestbook">방명록</a></li>
			<li><a href="<%=path%>/board">게시판</a></li>
		</ul>
	</div>
</body>
</html>