<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="admin.panel.title" /></title>

<LINK rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/>
<LINK rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
<div class="container">

<jsp:include page="/WEB-INF/views/components/header.jsp" />

<div class="jumbotron" style="width: 600px;">
<div class="list-group">
	<c:url value='/admin/activation_requests' var="adminActivationRequestsUrl"/>
	<a class="list-group-item list-group-item-success" href="${adminActivationRequestsUrl}"><spring:message code='admin.activate_accounts.requests.title'/></a>

	<c:url value='/admin/users' var="adminUsersUrl"/>
	<a class="list-group-item list-group-item-success" href="${adminUsersUrl}"><spring:message code='admin.users.title'/></a>
	
	<c:url value='/admin/genres' var="adminGenresUrl"/>
	<a class="list-group-item list-group-item-success" href="${adminGenresUrl}"><spring:message code='admin.genres.title'/></a>
	
	<c:url value='/admin/pending_events' var='adminPendingEventsUrl' />
	<a class="list-group-item  list-group-item-success" href="${adminPendingEventsUrl}"><spring:message code="events.pending.title"/></a>

	<c:url value='/admin/reports' var='adminReportsUrl' />
	<a class="list-group-item list-group-item-success" href="${adminReportsUrl}"><spring:message code="admin.reports.title"/></a>
</div>
</div>
</div>
</body>
</html>