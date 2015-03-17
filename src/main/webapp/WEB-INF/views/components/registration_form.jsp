<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
<script type="text/javascript" src="resources/js/registration_form.js"></script>
	<c:url value="/register" var="registerUrl"></c:url>
	
	<form:form onsubmit="return validateForm();" action="${registerUrl }" commandName="userForm">
		<table>
		<tr>
			<td><spring:message code="label.username" /></td>
			<td><form:input onblur="validateUsername()" id="input_username" path="username" /></td>
			<td><form:errors path="username" cssClass="error"/></td>
			<td id="username_error_slot"></td>
		</tr>
		<tr>
			<td><spring:message code="label.password"/></td>
			<td><form:password onblur="validatePassword()" id="input_password" path="rawPassword"/></td>
			<td><form:errors path="rawPassword" cssClass="error"/></td>
			<td id="password_error_slot"></td>
		</tr>
		<tr>
			<td><spring:message code="label.repeat_password"/></td>
			<td><input onblur="validateRepeatePassword()" id="input_repeat_password" type="password"/></td>
			<td id="repeat_password_error_slot"></td>
		</tr>
		<tr>
			<td><spring:message code="label.firstName"/></td>
			<td><form:input path="firstName"/></td>
			<td><form:errors path="firstName" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.lastName"/></td>
			<td><form:input path="lastName"/></td>
			<td><form:errors path="lastName" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.jmb"/></td>
			<td><form:input path="jmb"/></td>
			<td><form:errors path="jmb" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.email"/></td>
			<td><form:input path="email" /></td>
			<td><form:errors path="email" cssClass="error"/></td>
		</tr>
		<tr>
			<td><input value="<spring:message code='label.submit.registration'/>" type="submit" /></td>
		</tr>
		</table>
	</form:form>

	<span style='display: none' id="username_error"><spring:message code="Pattern.userForm.username" /></span>
	<span style='display: none' id="password_error"><spring:message code="Pattern.userForm.rawPassword" /></span>
	<span style='display: none' id="repeat_password_error"><spring:message code="no_match.userForm.repeatPassword" /></span>

</div>	