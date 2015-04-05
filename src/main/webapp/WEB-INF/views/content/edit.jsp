<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:url value='/content/edit' var="contentEditUrl" />
	
	<form:form action="${contentEditUrl}" modelAttribute="changeContentInfoForm" method="post">
	
		<table>
			<tr>
				<td><spring:message code="content.new.label.name" /></td>
				<td><form:input path="name"/></td>
				<td><form:errors path="name" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.artist" /></td>
				<td><form:input path="artist"/></td>
				<td><form:errors path="artist" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.genre" /></td>
				<td>
					<form:select path="genre">
						<form:options items="${genres}"/>						
					</form:select>
				</td>
				<td><form:errors path="genre" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.lyrics" /></td>
				<td><form:textarea path="lyrics" /></td>
				<td><form:errors path="lyrics" /></td>
			</tr>
			<tr>
				<td><input value="<spring:message code="label.general.submit" />" type="submit" /></td>
				<td><form:hidden path="id"/></td>
			</tr>
		</table>
	
	</form:form>
	
	<div>
		<form action="<c:url value="/content/delete" />" method="post">
			<input type="hidden" name="mcid" value="${changeContentInfoForm.id}"/>
			<input type="submit" value="<spring:message code="label.general.delete" />"/>
		</form>
	</div>

</body>
</html>