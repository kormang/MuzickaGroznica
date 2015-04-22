<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="super.audio_upload.error.title" /></title>
<%@ include file="/WEB-INF/views/common_includes.jsp"  %>
</head>
<body>

	<jsp:include page="/WEB-INF/views/components/header.jsp" />

	<spring:message code="super.audio_upload.error" />
</body>
</html>