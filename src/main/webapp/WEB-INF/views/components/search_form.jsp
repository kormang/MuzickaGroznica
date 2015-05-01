<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
	<c:url value="/content/search" var="searchFormActionUrl"/>
	<form:form action="${searchFormActionUrl}" method="get" modelAttribute="searchForm">
		<table class="search">
			<tr>
				<td><div class="input-group-btn"><input type="text" name="name" class="form-control" placeholder="<spring:message code="label.general.name" />"/></div></td>
				
				<td><div class="input-group-btn"><input type="text" name="artist" class="form-control" placeholder="<spring:message code="label.general.artist" />"/></div></td>
				
				<td><div class="input-group-btn"><input type="text" name="genre"  class="form-control" placeholder="<spring:message code="label.general.genre" />"/></div></td>
				
				<td><input type="submit" class="btn btn-primary" value='<spring:message code="label.general.search" />' /></td>
			</tr>		
		</table>
	</form:form>