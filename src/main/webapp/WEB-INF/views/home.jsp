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

<c:if test="${not empty user }">
	Hello ${user.username }
	<a href="<c:url value='/content/new'/>" ><spring:message code="content.new.title" /></a>
</c:if>


<c:if test="${not empty ROLE_SUPER }">
	<a href="<c:url value='/super/main'/>" >Super main</a>
	<a href="<c:url value='/super/event/new'/>" ><spring:message code="event.new.title" /></a>
</c:if>
<c:if test="${not empty ROLE_ADMIN }">
	<a href="<c:url value='/admin/panel' />"><spring:message code='admin.panel.title'/></a>
</c:if>

<spring:message code="home.test_message" />

<c:if test="${empty user }">
	<div style="float: right;" >
		<jsp:include page="components/registration_form.jsp" />
	</div>
</c:if>

<div style="float: left;">
		<jsp:include page="components/search_form.jsp" />		
</div>


</body>
</html>
