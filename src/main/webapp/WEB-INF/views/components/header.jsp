<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style='top:0; right:0; float: right;'>

	<c:if test="${empty user }">
		<form name="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post">
			<table>
				<tr>
					<td>Корисничко име:</td>
					<td> <input type="text" name="username" /></td>
					<td>Лозинка: </td>
					<td> <input type="password" name="password" /> </td>
					<td> <input type="submit" name="command" value="Улогуј се" /></td>
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${not empty user}">
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<a href="${logoutUrl}"><spring:message code="general.label.logout"/></a>
			
	</c:if>
	
	<span style="float: right">
		<c:url value="/user/settings" var="userSettingsUrl" />
		<a href="${userSettingsUrl }"><spring:message code="user.settings.title"/></a>
		|
		&nbsp;
		|
	    <a href="?lang=sr">sr</a>
	    |
	    <a href="?lang=en">en</a>
	</span>

</div>

    