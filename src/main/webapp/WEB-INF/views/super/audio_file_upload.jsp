<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="super.audio_upload.title" /></title>

 <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/>
 <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
	
</head>
<body>
<div class="container">
		<jsp:include page="/WEB-INF/views/components/header.jsp" />
		
		<div class="jumbotron classic-jumbotron">
			<c:url value='/super/audio_file_uploader' var="audioFileUploadUrl"/>
			<h4><a href='<c:url value="/content/new" />'><spring:message code="content.new.title" /></a>
			</h4>
		<form:form action="${audioFileUploadUrl}" method="post" enctype="multipart/form-data">
		
		<table class="table">
			<tr>
				<td><spring:message code="content.new.label.name" /></td>
				<td><input class="form-control" type="text" name="name"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.artist" /></td>
				<td><input class="form-control" type="text" name="artist"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.genre" /></td>
				<td>
					<select class="btn btn-default" name="genre">
						<c:forEach items="${genres}" var="i"><option value="${i}">${i}</option></c:forEach>						
					</select>
				</td>
			</tr>
			<tr>
				<td><spring:message code="super.audio_upload.label.filе" /></td>
				<td><input type="file" name="file" accept="audio/*"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.lyrics" /></td>
				<td><textarea class="form-control" name="lyrics" ></textarea></td>
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