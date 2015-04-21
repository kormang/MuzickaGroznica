<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="avatar_upload.title" /></title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>

	<div class="container">

	<jsp:include page="/WEB-INF/views/components/header.jsp" />
	
	<div class="jumbotron classic-jumbotron">

	<c:url value="/user/settings/avatar" var="avatarUploadUrl"></c:url>
	
	
	<form:form action="${avatarUploadUrl }" modelAttribute="avatarUploadForm" enctype="multipart/form-data">
		<table class="table">
		<tr>
			<td><spring:message code="avatar_upload.file.label" /></td>
			<td><input type="file" name="file" id="file" accept="image/*"/></td>
			<td class="registration-form small error"><form:errors path="file"/></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" value="<spring:message code='label.general.submit'/>" type="submit" /></td>
		</tr>
		</table>
	</form:form>

	</div>
</div>
</body>
</html>