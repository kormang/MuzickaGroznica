<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
 <%@ include file="/WEB-INF/views/common_includes.jsp"  %>
</head>
<body>
<div class="container">

<jsp:include page="/WEB-INF/views/components/header.jsp" />

<div class="jumbotron" style="width: 600px;">

	<table class="table">
		<tr><td>${event.name}</td></tr>
		<tr><td>${event_dateAndTime}</td></tr>
		<tr><td>${event.location}</td></tr>
		<tr><td>${event.description}</td></tr>
	</table>
</div>
</div>
</body>
</html>