<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.activate_accounts.requests.title"/></title>

<%@ include file="/WEB-INF/views/common_includes.jsp"  %>
</head>
<body>

<div class="container">

<jsp:include page="/WEB-INF/views/components/header.jsp" />

<c:url value="/admin/activate_account" var="activateAccountUrl"/>

	<form:form action="${activateAccountUrl}" method="post">
		<table class="table">
			<c:forEach items="${users}" var="u">
			
				<tr>
					<td><c:out value="${u.username}"/></td>
					<td><c:out value="${u.lastName}"/></td>
					<td><c:out value="${u.firstName}"/></td>
					<td><c:out value="${u.jmb}"/></td>
					<td><c:out value="${u.registrationTime}"/></td>
					<td><input class="btn btn-primary" type="submit" name="_aid_${u.id}" value="<spring:message code='admin.activate_accounts.activate' />" /></td>
				</tr>
			
			</c:forEach>
		
		</table>
	
	</form:form>
</div>
</body>
</html>