<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
	<c:url value="/content/search" var="searchFormActionUrl"/>
	<form:form action="${searchFormActionUrl}" method="get" modelAttribute="searchForm">
		<table class="pretraga">
			<tr>
				<td><div class="input-group-btn"><form:input path="name" class="form-control" placeholder="Назив"/></div></td>
				
				<td><div class="input-group-btn"><form:input path="artist" class="form-control" placeholder="Извођач"/></div></td>
				
				<td><div class="input-group-btn"><form:input path="genre"  class="form-control" placeholder="Жанр"/></div></td>
				
				<td><input type="submit" class="btn btn-primary" value='<spring:message code="label.general.search" />' /></td>
			</tr>		
		</table>
	</form:form>