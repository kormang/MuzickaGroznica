<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:forEach items="${contents}" var="ct">
		<div><a href="<c:url value="/content/listen/${ct.id}"/>">${ct.name}</a></div>
	</c:forEach>