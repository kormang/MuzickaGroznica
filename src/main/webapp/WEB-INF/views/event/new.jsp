<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
 <%@ include file="/WEB-INF/views/common_includes.jsp"  %>

 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.base.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.bootstrap.css"/>" type="text/css" />
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcore.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxdatetimeinput.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcalendar.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxtooltip.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxbuttons.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxscrollbar.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxlistbox.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxdropdownlist.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/globalization/globalize.js"/>"></script>


</head>
<body>
<div class="container">
<script type="text/javascript">
    $(document).ready(function () {
    	setformdate = function(e){
    		var jsDate = e.args.date;
    		var hd = $("#hd").val(jsDate.getTime());
    	}
    	
        $("#dti").jqxDateTimeInput({ theme: "bootstrap", width: '250px', height: '25px', formatString: 'dd.MM.yyyy. HH:mm:ss', min: new Date() });
        $("#dti").on('valueChanged', setformdate);
        $('#dti').jqxDateTimeInput('setDate', new Date());

    });
</script>

  <jsp:include page="/WEB-INF/views/components/header.jsp" />

  <c:url value="/super/event/new" var="newEventActionUrl"/>
  
 <div class="jumbotron" style="width: 600px;">
  <form:form id="form" action="${newEventActionUrl}" method="post" modelAttribute="newEventForm">

	<table class="table">
	
	<tr>
		<td><spring:message code="event_new.name" /></td>
		<td><form:input class="form-control" path="name" /></td>
		<td><form:errors path="name" /></td>
	</tr>
	
	<tr>
		<td><spring:message code="event_new.description" /></td>
		<td><form:input class="form-control" path="description" /></td>
		<td><form:errors path="description" /></td>
	</tr>
	
	<tr>
		<td><spring:message code="event_new.location" /></td>
		<td><form:input class="form-control" path="location" /></td>
		<td><form:errors path="location" /></td>
	</tr>

	<tr>
		<td>
			<spring:message code="event_new.eventTime"/>
		</td>
		<td>
		 	<div id='dti'></div>
		 	<form:hidden path="eventTime" id="hd"/>
	 	</td>
	 	<td><form:errors path="eventTime" /></td>
 	</tr>
 	
 	<tr>
	 	<td>
	 		<input type="submit" class="btn btn-primary" value="<spring:message code="label.general.submit"/>"/>
	 	</td>
 	</tr>
 	</table>
 </form:form>
 </div>
</div>
</body>
</html>