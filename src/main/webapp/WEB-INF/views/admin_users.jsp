<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.users.title" /></title>

<c:url value="/resources/js/admin_users.js" var="jsUrl" />
<script type="text/javascript" src="${jsUrl }"></script>

</head>
<body>

	

	<span style="display: none;" id="ajax_role_switch_address"><c:url value="/admin/role_switch" /></span>

	<table>
		
		<c:forEach items="${users}" var="u" varStatus="status">
		
			<tr>
				<td>${u.username}</td>
				<td>${u.lastName}</td>
				<td>${u.firstName}</td>
				<td>${u.email}</td>
				<td>${u.username}</td>
				<td>${u.registrationTime}</td>
				<td>${u.activationTime}</td>
				<td>
					<input type="checkbox" onchange="switchRole(this, 0, ${u.id})" <c:if test="${superFlags[status.count -1]}">checked="checked"</c:if>  /><spring:message code="roles.role_super" />
				</td>
				<td>
					<input type="checkbox" onchange="switchRole(this, 1, ${u.id})" <c:if test="${adminFlags[status.count -1]}">checked="checked"</c:if> /><spring:message code="roles.role_admin" />
				</td>
			</tr>
		
		</c:forEach>
		
	
	</table>
<br/>
	<div id="messagearea" >
	
	</div>

</body>
</html>