<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	<ul class="list-group">
		<c:forEach items="${comments}" var="comm">
		
			<li class="list-group-item">
				<h4><span class="label label-default">${comm.user.username}</span>
				
				<c:if test="${not empty ROLE_ADMIN}">
					<span style="float: right; color: red; weight: bold;">
						<a class="deletecomment btn btn-danger" id="_commid_${comm.id}" href="#">X</a>
					</span>
				</c:if>
				</h4>
				<div>
					${comm.commentText}
				</div>
			</li>
					
		</c:forEach>
	</ul>