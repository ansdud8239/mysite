<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

				<form:form modelAttribute="userVo" id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="color: #ff0000; text-align: left; padding: 0;">
								<spring:message code="${errors.getFieldError('name').codes[0] }" />
							</p>
						</c:if>
					</spring:hasBindErrors>
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<input type="button" value="중복체크">
					<p style="color: #ff0000; text-align: left; padding: 0;">
						<form:errors path="email" />
					</p>
					<label class="block-label">
						<spring:message code="user.join.label.password" />
					</label>
					<form:password path="password" />
					<p style="color: #ff0000; text-align: left; padding: 0;">
						<form:errors path="password" />
					</p>
					<fieldset>
						<legend>성별</legend>
						<form:radiobutton path="gender" value="female" label="여" />
						<form:radiobutton path="gender" value="male" label="남" />
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					<input type="submit" value="가입하기">
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>