<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="content.favorites.title" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/views/components/header.jsp" />

	<div style="font-weight: bold;" id="pl_title"><spring:message code="content.favorites.title" /></div>
	
	<jsp:include page="/WEB-INF/views/content/list_content.jsp"></jsp:include>


</body>
</html>