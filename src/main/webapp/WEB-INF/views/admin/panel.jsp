<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="admin.panel.title" /></title>
</head>
<body>

	<c:url value='/admin/activation_requests' var="adminActivationRequestsUrl"/>
	<a href="${adminActivationRequestsUrl}"><spring:message code='admin.activate_accounts.requests.title'/></a>

	<c:url value='/admin/users' var="adminUsersUrl"/>
	<a href="${adminUsersUrl}"><spring:message code='admin.users.title'/></a>
	
	<c:url value='/admin/genres' var="adminGenresUrl"/>
	<a href="${adminGenresUrl}"><spring:message code='admin.genres.title'/></a>
	
	<c:url value='/admin/pending_events' var='adminPendingEventsUrl' />
	<a href="${adminPendingEventsUrl}"><spring:message code="events.pending.title"/></a>

</body>
</html>