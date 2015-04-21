<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:forEach items="${contents}" var="ct">
		<a class="list-group-item" href="<c:url value="/content/listen/${ct.id}"/>">${ct.name}</a>
	</c:forEach>