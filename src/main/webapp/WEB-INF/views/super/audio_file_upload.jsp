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
</head>
<body>
		<jsp:include page="/WEB-INF/views/components/header.jsp" />
		
		<c:url value='/super/audio_file_uploader' var="audioFileUploadUrl"/>

		<a href='<c:url value="/content/new" />'><spring:message code="content.new.title" /></a>
	
		<form:form action="${audioFileUploadUrl}" method="post" enctype="multipart/form-data">
	
		<table>
			<tr>
				<td><spring:message code="content.new.label.name" /></td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.artist" /></td>
				<td><input type="text" name="artist"/></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.genre" /></td>
				<td>
					<select name="genre">
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
				<td><textarea name="lyrics" ></textarea></td>
			</tr>
			<tr>
				<td><input value="<spring:message code="label.general.submit" />" type="submit" /></td>
			</tr>
		
		
		</table>
	
	</form:form>

</body>
</html>