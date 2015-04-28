<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><spring:message code="user.settings.title"/></title>
<%@ include file="/WEB-INF/views/common_includes.jsp"  %>
</head>
<body>
<div class="container">

<jsp:include page="/WEB-INF/views/components/header.jsp" />

<div class="jumbotron" style="width: 600px;">
	<c:url value="/user/settings/password" var="chpassUrl" />
	<c:url value="/user/settings/avatar" var="avatarUploadUrl" />

	<h4>
		<a href='${chpassUrl }'><spring:message code="label.change_password" /></a><br />
	
		<a href='${avatarUploadUrl }'><spring:message code="avatar_upload.title" /></a>
	</h4>	
	
	<div>
	<c:url value="/user/settings" var="registerUrl"></c:url>
	
	<form:form action="${registerUrl }" commandName="userSettingsForm">
		<table class="table">
		<tr>
			<td><spring:message code="label.username" /></td>
			<td><c:out value="${userSettingsForm.username}"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.old_password"/></td>
			<td><form:password  class="form-control" style="width: 200px" id="input_old_password" path="oldPassword"/></td>
			<td class="registration-form small error"><form:errors path="oldPassword" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.firstName"/></td>
			<td><form:input class="form-control" path="firstName"/></td>
			<td class="registration-form small error"><form:errors path="firstName" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.lastName"/></td>
			<td><form:input class="form-control" path="lastName"/></td>
			<td class="registration-form small error"><form:errors path="lastName" cssClass="error"/></td>
		
		</tr>
		<tr>
			<td><spring:message code="label.jmb"/></td>
			<td><form:input class="form-control" path="jmb"/></td>
			<td class="registration-form small error"><form:errors path="jmb" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.email"/></td>
			<td><form:input class="form-control" path="email" /></td>
			<td class="registration-form small error"><form:errors path="email" cssClass="error"/></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary"value="<spring:message code='label.general.submit'/>" type="submit" /></td>
		</tr>
		</table>
	</form:form>

	<span style='display: none' id="username_error"><spring:message code="Pattern.userForm.username" /></span>
	<span style='display: none' id="password_error"><spring:message code="Pattern.userForm.rawPassword" /></span>
	<span style='display: none' id="repeat_password_error"><spring:message code="no_match.userForm.repeatPassword" /></span>
</div>
</div>
</div>
</body>
</html>