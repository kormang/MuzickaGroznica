<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
  	

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@ include file="/WEB-INF/views/common_includes.jsp"  %>
</head>
<body>
	<jsp:include page="/WEB-INF/views/components/header.jsp" />
	<c:url value="/home" var="homeUrl" />
	<a class="btn" href='${homeUrl }'>>></a>

</body>
</html>
