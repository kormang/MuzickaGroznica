<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="content.playlists" /></title>

<LINK rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/>
<LINK rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
<div class="container">
<jsp:include page="/WEB-INF/views/components/header.jsp" />

<div class="jumbotron" style="width: 600px;">
		<h3><span class="label label-warning"><spring:message code="content.playlists" /></span></h3>

<table class="table">

	<c:forEach items="${playlists}" var="playlist">
		<tr>
			<c:url value="/content/view_playlist_content?plid=${playlist.id}" var="viewurl"/>
			<td><a href="${viewurl}" >${playlist.title}</a></td>
			<td>${playlist.creationTime}</td>
			<td>${playlist.numberOfContents}</td>
		</tr>
	</c:forEach>

</table>
</div>
</div>
</body>
</html>