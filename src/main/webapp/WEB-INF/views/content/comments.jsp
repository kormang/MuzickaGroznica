<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



	<c:forEach items="${comments}" var="comm">
	
	<div>
		<div style="border: 2px solid blue; display: inline-block;">
		
			<span style="float: left;">${comm.user.username}</span>
			
			<c:if test="${not empty ROLE_ADMIN}">
				<span style="float: right; color: red; weight: bold;">
					<a id="_commid_${comm.id}" class="deletecomment" href="#">X</a>
				</span>
			</c:if>
			
			<div>
				${comm.commentText}
			</div>
			
		</div>
	</div>
				
	</c:forEach>