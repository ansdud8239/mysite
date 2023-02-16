<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/hearder.jsp" />
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/update">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">
					<label class="block-label" for="email">이메일</label>
					<h4>${userVo.email }</h4>
					<input type="hidden" name="email" value="${userVo.email }">
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					<fieldset>
						<legend>성별</legend>
						<label>여</label>
						<c:choose>
							<c:when test='${"female"==userVo.gender}'>
								<input type="radio" name="gender" value="female" checked="checked">
								<label>남</label>
								<input type="radio" name="gender" value="male">
							</c:when>
							<c:otherwise>
								<input type="radio" name="gender" value="female">
								<label>남</label>
								<input type="radio" name="gender" value="male">
							</c:otherwise>
						</c:choose>
					</fieldset>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>