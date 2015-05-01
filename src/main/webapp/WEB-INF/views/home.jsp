<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title><spring:message code="muzickagroznica.application_name"/></title>
	<%@ include file="/WEB-INF/views/common_includes.jsp"  %>
	 
	 <script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>


</head>
<body>
<div class="container">
<script type="text/javascript">
	var params = {
			"newsfeedformatedUrl": "<c:url value='/newsfeedformated'/>"
	};

	$(document).ready(function(){
		inithome(params);	
	});
</script>


<jsp:include page="/WEB-INF/views/components/header.jsp" />
<c:if test="${not empty user }">

	<nav class="navbar navbar-inverse">
	<ul class="nav navbar-nav">
	<li><a href="<c:url value='/content/my_content'/>" ><spring:message code="content.my.title" /></a></li>
	<li><a href="<c:url value='/content/new'/>" ><spring:message code="content.new.title" /></a></li>
	<li><a href="<c:url value='/content/view_user_playlists'/>" ><spring:message code="content.playlists" /></a></li>
	<li><a href="<c:url value='/content/favorites'/>" ><spring:message code="content.favorites.title" /></a></li>
	
	<c:if test="${not empty ROLE_SUPER }">
		<li><a href="<c:url value='/super/event/new'/>" ><spring:message code="event.new.title" /></a></li>
	</c:if>
	<c:if test="${not empty ROLE_ADMIN }">
		<li><a href="<c:url value='/admin/panel' />"><spring:message code='admin.panel.title'/></a></li>
	</c:if>

	</ul>
	</nav>
</c:if>

<div style="float: left;">
		<jsp:include page="components/search_form.jsp" />		
</div>
<div class="ispod_TRAZI">

<!--<spring:message code="home.test_message" />-->
<!-- Single button -->
<c:if test="${not empty user }">
	<div class="panel panel-primary role">
	<div class="panel-heading" id="pl_title"><spring:message code="content.recommended" /></div>
	<div class="panel-body">
	<div class="list-group">
	<c:forEach items="${recommended}" var="ct">
		<a class="list-group-item" href="<c:url value="/content/listen/${ct.id}"/>"><c:out value="${ct.name}"/></a>
	</c:forEach>
	</div>
	</div>
	</div>
</c:if>

</div>
<c:if test="${empty user }">
	<div style="float: right;" >
		<jsp:include page="components/registration_form.jsp" />
	</div>
</c:if>

<div class="newsf" id="newsfeedarea"></div>

</div>


</body>
</html>
