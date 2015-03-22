<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="content.new.title" /></title>
</head>
<body>
	<c:url value='/content/new' var="contentNewUrl" />

	<form:form action="${contentNewUrl}" modelAttribute="contentNewForm" method="post">
	
		<table>
			<tr>
				<td><spring:message code="content.new.label.name" /></td>
				<td><form:input path="name"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.artist" /></td>
				<td><form:input path="artist"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.genre" /></td>
				<td><form:input path="genre"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.contentPath" /></td>
				<td><form:input path="contentPath"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.lyrics" /></td>
				<td><form:textarea path="lyrics" /></td>
			</tr>
			<tr>
				<td><input value="<spring:message code="label.submit.general" />" type="submit" /></td>
			</tr>
		
		
		</table>
	
	</form:form>

</body>
</html>