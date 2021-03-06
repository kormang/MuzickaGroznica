<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="muzickagroznica.application_name"/></title>
	<%@ include file="/WEB-INF/views/common_includes.jsp"  %>
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/views/components/header.jsp" />
	<jsp:include page="/WEB-INF/views/components/search_form.jsp" />
	
	<div class="jumbotron classic-jumbotron">
	<table class="table">
	<c:choose>
	
		<c:when test="${empty user}">
	
	
		<c:forEach items="${searchResults}" var="i" varStatus="status">
			<tr>
				<td><c:out value="${i.name}"/></td>
				<td><c:out value="${formattedDates[status.count-1]}"/></td>
			</tr>
		</c:forEach>
		
		
		</c:when>
		
		
		<c:otherwise>
		
			<c:forEach items="${searchResults}" var="i" varStatus="status">
				<tr>
					<td><a href='<c:url value="/content/listen/${i.id}" />'><c:out value="${i.name}"/></a></td>
					<td><c:out value="${formattedDates[status.count-1]}"/></td>
				</tr>
			</c:forEach>
		
		</c:otherwise>
	
		
	</c:choose>
	</table>
	</div>
 </div>
</body>
</html>