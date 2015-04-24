<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="content.new.title" /></title>
 <%@ include file="/WEB-INF/views/common_includes.jsp"  %>

 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.base.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.arctic.css"/>" type="text/css" />

 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcore.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxinput.js"/>"></script>

 <script type="text/javascript" src="<c:url value="/resources/js/content/neworedit.js" />"></script>

</head>
<body>
<div class="container">
	<script type="text/javascript">
		var params = {
			"availableArtistsUrl" : "<c:url value="/content/available_artists" />"	
			
		};
	
	
		$(document).ready(
			function(){
				initnoe(params);
			}		
		);
	
	</script>

<jsp:include page="/WEB-INF/views/components/header.jsp" />

	<c:url value='/content/new' var="contentNewUrl" />
	
<div class="jumbotron" style="width: 600px;">
	<c:if test="${not empty ROLE_SUPER}" >
		<h4><a href="<c:url value='/super/audio_file_upload' />"><spring:message code="super.audio_upload.title"/></a></h4>
	</c:if>

	<form:form action="${contentNewUrl}" modelAttribute="contentNewForm" method="post">
	
		<table class="table">
			<tr>
				<td><spring:message code="content.new.label.name" /></td>
				<td><form:input class="form-control" path="name"/></td>
				<td><form:errors path="name" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.artist" /></td>
				<td><form:input id="artist" path="artist" class="form-control"/></td>
				<td><form:errors path="artist" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.genre" /></td>
				<td>
					<form:select class="btn btn-default" path="genre">
						<form:options items="${genres}"/>						
					</form:select>
				</td>
				<td><form:errors path="genre" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.contentPath" /></td>
				<td><form:input class="form-control" path="contentPath"/></td>
				<td><form:errors path="contentPath" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.lyrics" /></td>
				<td><form:textarea class="form-control" path="lyrics" /></td>
				<td><form:errors path="lyrics" /></td>
			</tr>
			<tr>
				<td><input class="btn btn-primary" value="<spring:message code="label.general.submit" />" type="submit" /></td>
			</tr>
		
		
		</table>
	
	</form:form>
</div>
</div>
</body>
</html>