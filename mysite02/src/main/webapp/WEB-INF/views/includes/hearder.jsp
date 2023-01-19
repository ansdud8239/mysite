<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	UserVo authUser=(UserVo)session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<h1>MySite</h1>
		<ul>
			<% if(authUser == null){%>
			<li><a href="<%=path%>/user?a=loginform">로그인</a></li>
			<li><a href="<%=path%>/user?a=joinform">회원가입</a></li>
			<%}else{ %>
			<li><a href="<%=path%>/user?a=updateform">회원정보수정</a></li>
			<li><a href="<%=path%>/user?a=logout">로그아웃</a></li>
			<li><%=authUser.getName() %>님 안녕하세요 ^^;</li>
			<%} %>
		</ul>
	</div>
</body>
</html>