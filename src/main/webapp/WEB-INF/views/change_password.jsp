<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.change_password"/></title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
	<div class="container">
	<jsp:include page="/WEB-INF/views/components/header.jsp" />
	
	<div class="jumbotron classic-jumbotron">
	<c:url value="/resources/js/change_password_form.js" var="cpjsscriptUrl" />
	<script type="text/javascript" src="${cpjsscriptUrl }"></script>
	
	<c:url value="/user/settings/password" var="registerUrl"></c:url>
	
	
	<form:form onsubmit="return validateForm();" action="${registerUrl }" commandName="userPasswordForm">
		<table class="table">
		<tr>
			<td><spring:message code="label.old_password"/></td>
			<td><form:password  id="input_old_password" path="oldPassword" class="form-control" style="width: 200px"/></td>
			<td></td>
			<td class="registration-form small"><form:errors path="oldPassword" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.password"/></td>
			<td><form:password class="form-control" onblur="validatePassword()" id="input_password" path="rawPassword"/></td>
			<td class="registration-form small"><form:errors path="rawPassword" cssClass="error"/></td>
			<td class="registration-form small error" id="password_error_slot"></td>
		
		</tr>
		<tr>
			<td><spring:message code="label.repeat_password"/></td>
			<td><input class="form-control" onblur="validateRepeatePassword()" id="input_repeat_password" type="password"/></td>
			<td class="registration-form small error" id="repeat_password_error_slot"></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" value="<spring:message code='label.general.submit'/>" type="submit" /></td>
		</tr>
		</table>
	</form:form>

	<span style='display: none' id="password_error"><spring:message code="Pattern.userPasswordForm.rawPassword" /></span>
	<span style='display: none' id="repeat_password_error"><spring:message code="no_match.userForm.repeatPassword" /></span>

	</div>
	
</div>
</body>
</html>