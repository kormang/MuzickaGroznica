<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body onload='document.loginForm.username.focus();'>
	
	<form name="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post">

		<table>
			<tr>
				<td>Username:</td><td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Password:</td><td><input type="password" name="password"/></td>
			</tr>
			<tr><td><input type="submit" value="Login" /></td></tr>
		</table>

		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

		
	
	</form>
	
</body>
</html>