<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="content.my.title" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/views/components/header.jsp" />

	<div style="font-weight: bold;" id="pl_title"><spring:message code="content.my.title" /></div>
	
<form action="<c:url value="/content/view_edit" />" method="post">
<table>
	<c:forEach items="${contents}" var="ct">
		<tr>
			<td><a href="<c:url value="/content/listen/${ct.id}"/>">${ct.name}</a></td>
			<td><input type="submit" name="_mcid_${ct.id}" value="<spring:message code="label.general.edit" />"/></td>
		</tr>
	</c:forEach>
</table>
</form>
</body>
</html>