<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<c:forEach items="${feeds}" var="feed">
		<div>
			<a href="${feed.link}"><c:out value="${feed.title}"/></a>
			<div><c:out value="${feed.description.value}"/></div>
		</div>
	</c:forEach>