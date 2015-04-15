<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.reports.title"/></title>

 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.base.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.arctic.css"/>" type="text/css" />

 <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.js" />"></script>

 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcore.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxdata.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.selection.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxscrollbar.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxbuttons.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxribbon.js"/>"></script>
 
 <script type="text/javascript" src="<c:url value="/resources/js/admin/reports.js" />"></script>
	

</head>
<body>
<script>

	params = {
			headers: {
				name: "<spring:message code='admin.reports.headers.name'/>",
				artist: "<spring:message code='admin.reports.headers.artist'/>",
				genre: "<spring:message code='admin.reports.headers.genre'/>",
				publishDate: "<spring:message code='admin.reports.headers.publishDate'/>",
				rating: "<spring:message code='admin.reports.headers.rating'/>",
				contentType: "<spring:message code='admin.reports.headers.contentType'/>",
				favored: "<spring:message code='admin.reports.headers.favored'/>"	
			},
			otherlocals: {
				dateformat: "<spring:message code='muzickagroznica.dateFormat'/>"	
			},
			topRatedUrl: "<c:url value='/admin/reports/get_top_rated' />",
			mostFavoredUrl: "<c:url value='/admin/reports/get_most_favored' />",
			monthlyReportDataUrl: "<c:url value='/admin/reports/get_monthly_report_data' />"
			
	};
	
	$(document).ready(
		function(){
			
			
			initreports(params);
		}		
	);
	
</script>

<jsp:include page="/WEB-INF/views/components/header.jsp" />

	<div id="ribbon">
	    <ul>
	        <li><spring:message code="admin.reports.toprated"/></li>
	        <li><spring:message code="admin.reports.mostfavored"/></li>
	        <li><spring:message code="admin.reports.monthlystats"/></li>
	    </ul>
	    <div>
	        <div>
	        	<div id="topRatedGrid"></div>
	        </div>
	        <div>
	        	<div id="mostFavoredGrid"></div>
	        </div>
	        <div>
	        	<p><span><spring:message code="admin.reports.monthlystats.num_added_content"/> </span><span id="num_a_c"></span>.</p>
	        	<p><span><spring:message code="admin.reports.monthlystats.num_reg_users"/> </span><span id="num_r_u"></span>.</p>
	        </div>
	    </div>
	</div>
	
	<div class="log">
	</div>

</body>
</html>