<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<!-- <c:if test="${not empty user }"> -->
	Hello ${user.username }
<!-- </c:if> -->


<c:if test="${not empty ROLE_SUPER }">
	<a href="<c:url value='/super/main'/>" >Super main</a>
</c:if>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<a href="${logoutUrl}">Log Out</a>
</body>
</html>
