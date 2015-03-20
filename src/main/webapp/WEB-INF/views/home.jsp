<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Home</title>
</head>
<body>

<jsp:include page="components/header.jsp" />

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<!-- <c:if test="${not empty user }"> -->
	Hello ${user.username }
<!-- </c:if> -->


<c:if test="${not empty ROLE_SUPER }">
	<a href="<c:url value='/super/main'/>" >Super main</a>
</c:if>

<spring:message code="home.test_message" />

<jsp:include page="components/registration_form.jsp" />

</body>
</html>
