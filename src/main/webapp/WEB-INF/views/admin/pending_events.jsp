<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="events.pending.title"/></title>
 <%@ include file="/WEB-INF/views/common_includes.jsp"  %>

 <script type="text/javascript" src="<c:url value="/resources/js/admin/pending_events.js" />"></script>

</head>
<body>
<div class="container">

<jsp:include page="/WEB-INF/views/components/header.jsp" />

<script type="text/javascript">
var approvalUrl = "<c:url value="/admin/event_approval" />";
var params = {
		"approvalUrl": approvalUrl
};
	$(document).ready(
		function(){
			initpe(params);
		}
	);
</script>
	<div class="jumbotron" style="width: 600px;">
		<c:forEach items="${events}" var="ev">
			<div>
				<spring:message code="events.name"/>: <span>${ev.name}</span>.
				<spring:message code="events.location"/>: <span>${ev.location}</span>.
				<spring:message code="events.publishTime"/>: <span>${ev.publishTime}</span>.
				<spring:message code="events.eventTime"/>: <span>${ev.eventTime}</span>.
				<div>
					<spring:message code="events.description"/>: <br/>
					<div>
						${ev.description}
					</div>
					<br/>
				</div>
				<input type="button" class="btn btn-primary approvebutton" name="_eid_${ev.id}" value="<spring:message code="events.approve" />" />
				<input type="button" class="btn btn-primary disapprovebutton" name="_eid_${ev.id}" value="<spring:message code="events.disapprove" />" />
			</div>
				
		</c:forEach>
	</div>
</div>
</body>
</html>