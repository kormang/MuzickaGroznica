<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<LINK rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/>
<LINK rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/views/components/header.jsp" />

	<div class="jumbotron" style="width: 600px;">
		<div class="list-group">
			<div id="pl_title" class="list-group-item active">${playlist.title}</div>
			<jsp:include page="/WEB-INF/views/content/list_content.jsp"></jsp:include>
		</div>
	</div>
</div>
</body>
</html>