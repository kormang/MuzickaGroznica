<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body onload='document.loginForm.username.focus();'>
<div class="container">
	<div align="center" style="margin-top:100px;">
	<h1 class="error small">
	<c:choose>
		<c:when test="${badCredentials }">
			<spring:message code="spring.security.authenticationfailure.badCredentials"/>
		</c:when>
		<c:when test="${accountDisabled }">
			<spring:message code="spring.security.authenticationfailure.accountDisabled"/>
		</c:when>
	</c:choose>
	</h1>
	<div class="jumbotron" style="width: 400px;">
	<form name="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post">
		
		<table>
			<tr>
				<td><spring:message code="label.username"/></td><td><input class="form-control marg5" type="text" name="username" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.password"/></td><td><input class="form-control  marg5" type="password" name="password"/></td>
			</tr>
			<tr><td><input class="btn btn-primary" type="submit" value="<spring:message code="label.general.login"/>" /></td></tr>
		</table>

		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	</form>
	</div>
	</div>
</div>
</body>
</html>