<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
	<div>
	<c:url value="/content/search" var="searchFormActionUrl"/>
	<form:form action="${searchFormActionUrl}" method="get" modelAttribute="searchForm">
	
		<table>
			<tr>
				<td><spring:message code="search_form.label.name" /></td>
				<td><form:input path="name"/></td>
				
				<td><spring:message code="label.general.artist" /></td>
				<td><form:input path="artist"/></td>
				
				<td><spring:message code="label.general.genre" /></td>
				<td><form:input path="genre"/></td>
				
				<td><input type="submit" value='<spring:message code="label.general.search" />' /></td>
			</tr>		
		</table>

	</form:form>
	</div>
	
	