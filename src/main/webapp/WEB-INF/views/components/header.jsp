<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="header" >

<h1 class="mg-app-title">
	<kbd><a style="color: #fff" href="<c:url value='/home'/>" ><spring:message code="muzickagroznica.application_name" /></a></kbd>
</h1>


<div class="user" >
	<c:if test="${not empty user}">
	
		<c:choose>
			<c:when test="${not empty user.avatarPath }">
				<c:url value="/images/avatars/${user.avatarPath}" var="avatarUrl" />
			</c:when>
			<c:otherwise>
				<c:url value="/images/avatars/no-face.gif" var="avatarUrl" />
			</c:otherwise>
		</c:choose>
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<c:url value="/user/settings" var="userSettingsUrl" />
		
		<img src="${avatarUrl }" class="img-circle img-profile" />


	</c:if>

	<c:if test="${empty user }">
		<form name="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post">
			<table  class="pretraga">
				<tr>
					<td> <div class="input-group-btn"><input type="text" name="username" class="form-control" placeholder="Корисничко име"/></div></td>
					<td> <div class="input-group-btn"><input type="password" name="password" class="form-control" placeholder="Лозинка"/> </div></td>
					<td> <input type="submit" name="command" class="btn btn-info"value="<spring:message code="label.general.login"/>" /></td>
				</tr>
			</table>
		</form>
	</c:if>

</div>

		<div class="btn-group">
			  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
			  	O <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu" role="menu">
			  	<li><a href="${userSettingsUrl }"><spring:message code="user.settings.title"/></a></li>
			  	<li><a href="${logoutUrl}"><spring:message code="label.general.logout"/></a></li>
			  </ul>
			  
		</div>
		
				<div class="btn-group">
		  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
		    Action <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">Action</a></li>
		    <li><a href="#">Another action</a></li>
		    <li><a href="#">Something else here</a></li>
		    <li class="divider"></li>
		    <li><a href="#">Separated link</a></li>
		  </ul>
		</div>

</div>
    