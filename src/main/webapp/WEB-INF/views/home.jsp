<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<span style="float: right">
    <a href="?lang=sr">sr</a>
    |
    <a href="?lang=en">en</a>
</span>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

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

<!-- <c:if test="${not empty user }"> -->
	Hello ${user.username }
<!-- </c:if> -->


<c:if test="${not empty ROLE_SUPER }">
	<a href="<c:url value='/super/main'/>" >Super main</a>
</c:if>

<c:if test="${not empty user }">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<a href="${logoutUrl}">Log Out</a>
</c:if>



<spring:message code="home.test_message" />


</body>
</html>
