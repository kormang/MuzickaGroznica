<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.genres.title"/></title>
<c:url value="/resources/js/admin/genres.js" var="jsUrl" />
<script type="text/javascript" src="${jsUrl }"></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/components/header.jsp" />

	<span style="display: none;" id="defaulterrormsg"><spring:message code="ajax.default.error.message"/></span>
	<span style="display: none;" id="ajax_add_genre_address"><c:url value="/admin/add_genre" /></span>

	<div>
		<table>
			<tr>
				<td>
					<input id="genreName" type="text" name="name" onkeydown="if(event.keyCode == 13) addGenre()"/>
				</td>
				<td>
					<input type="button" onclick="addGenre()" value="<spring:message code='label.add' />"/>
				</td>
				<td>
					<span id="msgarea"></span>
				</td>
			</tr>
			
		</table>
	</div>
	
	<div id="genres">
		<c:forEach items="${genres}" var="g">
			<div>${g.name}</div>
		</c:forEach>
	</div>

</body>
</html>