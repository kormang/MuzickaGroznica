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

	<c:url value='/admin/activation_requests' var="adminPanelUrl"/>
	<a href="${adminPanelUrl }"><spring:message code='admin.activate_accounts.requests.title'/></a>


</body>
</html>