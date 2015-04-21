<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/resources/js/registration_form.js" var="rfjsscriptUrl" />
<script type="text/javascript" src="${rfjsscriptUrl }"></script>
	<c:url value="/register" var="registerUrl"></c:url>
	
	<form:form onsubmit="return validateForm();" action="${registerUrl }" commandName="userForm">
		<table class="registration">
		<tr>
			<td><spring:message code="label.username" /></td>
			<td class="registration-form"><form:input onblur="validateUsername()" id="input_username" path="username" class="form-control"/></td>
		</tr>
		<tr>
			<td><form:errors path="username" cssClass="error"/></td>
			<td  class="registration-form small error" id="username_error_slot"></td>
		</tr>
		<tr>
			<td><spring:message code="label.password"/></td>
			<td class="registration-form"><form:password onblur="validatePassword()" id="input_password" path="rawPassword" class="form-control"/></td>
		</tr>
		<tr>
			<td><form:errors path="rawPassword" cssClass="error"/></td>
			<td  class="registration-form small error" id="password_error_slot"></td>
		</tr>
		<tr>
			<td><spring:message code="label.repeat_password"/></td>
			<td class="registration-form"><input onblur="validateRepeatePassword()" id="input_repeat_password" type="password" class="form-control"/></td>
		</tr>
		<tr>
			<TD></TD>
			<td  class="registration-form small error" id="repeat_password_error_slot"></td>
		</tr>
		<tr>
			<td><spring:message code="label.firstName"/></td>
			<td  class="registration-form"><form:input path="firstName" class="form-control"/></td>
		</tr>
		<tr>
			<td></td>
			<td class="registration-form small"><form:errors path="firstName" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.lastName"/></td>
			<td class="registration-form"><form:input path="lastName" class="form-control"/></td>
		</tr>
		<tr>
			<td></td>
			<td class="registration-form small"><form:errors path="lastName" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.jmb"/></td>
			<td class="registration-form"><form:input path="jmb" class="form-control"/></td>
		</tr>
		<tr>
			<td></td>
			<td class="registration-form small"><form:errors path="jmb" cssClass="error"/></td>
		</tr>
		<tr>
			<td><spring:message code="label.email"/></td>
			<td class="registration-form"><form:input path="email"  class="form-control"/></td>
		</tr>
		<tr>
			<td></td>
			<td class="registration-form small"><form:errors path="email" cssClass="error"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input class="btn btn-info" value="<spring:message code='label.submit.registration'/>" type="submit" /></td>
		</tr>
		</table>
	</form:form>

	<span style='display: none' id="username_error"><spring:message code="Pattern.userForm.username" /></span>
	<span style='display: none' id="password_error"><spring:message code="Pattern.userForm.rawPassword" /></span>
	<span style='display: none' id="repeat_password_error"><spring:message code="no_match.userForm.repeatPassword" /></span>

	