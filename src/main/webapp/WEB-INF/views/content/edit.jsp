<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@ include file="/WEB-INF/views/common_includes.jsp"  %>

 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.base.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.bootstrap.css"/>" type="text/css" />

 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcore.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxinput.js"/>"></script>

 <script type="text/javascript" src="<c:url value="/resources/js/content/neworedit.js" />"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
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
<div class="container">

<jsp:include page="/WEB-INF/views/components/header.jsp" />

<div class="jumbotron" style="width: 600px;">
	<c:url value='/content/edit' var="contentEditUrl" />
	
	<form:form action="${contentEditUrl}" modelAttribute="changeContentInfoForm" method="post">
	
		<table>
			<tr>
				<td><spring:message code="content.new.label.name" /></td>
				<td><form:input path="name" class="form-control"/></td>
				<td><form:errors path="name" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.artist" /></td>
				<td><form:input id="artist" path="artist" /></td>
				<td><form:errors path="artist" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.genre" /></td>
				<td>
					<form:select path="genre" class="btn btn-info">
						<form:options items="${genres}"/>						
					</form:select>
				</td>
				<td><form:errors path="genre" /></td>
			</tr>
			<tr>
				<td><spring:message code="content.new.label.lyrics" /></td>
				<td><form:textarea path="lyrics" /></td>
				<td><form:errors path="lyrics" /></td>
			</tr>
			<tr>
				<td><input class="btn btn-info" value="<spring:message code="label.general.submit" />" type="submit" /></td>
				<td><form:hidden path="id"/></td>
			</tr>
		</table>
	
	</form:form>
	
	<div>
		<form action="<c:url value="/content/delete" />" method="post">
			<input class="btn btn-info" type="hidden" name="mcid" value="${changeContentInfoForm.id}"/>
			<input class="btn btn-info" type="submit" value="<spring:message code="label.general.delete" />"/>
		</form>
	</div>
</div>
</div>
</body>
</html> 
