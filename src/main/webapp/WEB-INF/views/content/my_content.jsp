<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="content.my.title" /></title>
<%@ include file="/WEB-INF/views/common_includes.jsp"  %>
</head>
<body>
<div class="container">

<jsp:include page="/WEB-INF/views/components/header.jsp" />

<div class="jumbotron" style="width: 600px;">
	<div id="pl_title"><h3><span class="label label-warning"><spring:message code="content.my.title" /></span></h3></div>
	
<form action="<c:url value="/content/view_edit" />" method="post">
<table class="table table-striped">
	<c:forEach items="${contents}" var="ct">
		<tr>
			<td><h4><a href="<c:url value="/content/listen/${ct.id}"/>">${ct.name}</a></h4></td>
			<td><input class="btn btn-primary" type="submit" name="_mcid_${ct.id}" value="<spring:message code="label.general.edit" />"/></td>
		</tr>
	</c:forEach>
</table>
</form>
</div>
</div>
</body>
</html>