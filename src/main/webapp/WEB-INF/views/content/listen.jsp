<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <title>${name} - <spring:message code="muzickagroznica.application_name"/></title>

 <%@ include file="/WEB-INF/views/common_includes.jsp"  %>

 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.base.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.bootstrap.css"/>" type="text/css" />

 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcore.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxrating.js"/>"></script>


 <script type="text/javascript" src="<c:url value="/resources/js/content/listen.js" />"></script>

</head>
<body>
<div class="container">
<script type="text/javascript">

var params = {
		"favoriteUrl" : "<c:url value="/content/favorite" />",
		"mcid" : <c:out value="${musicContentId}"/>,
		"rateUrl" : "<c:url value="/content/rate" />",
		"rateValue" : <c:choose><c:when test="${not empty rateValue}"><c:out value="${rateValue}"/></c:when><c:otherwise>null</c:otherwise></c:choose>,
		"favorite" : <c:out value="${favorite}"/>,
		"addCommentUrl" : "<c:url value="/content/add_comment" />",
		"commentTemplate": '<div style="border: 2px solid blue; display: inline-block;"><span style="float: left;">${user.username}</span><div>{{COMMENT_TEXT}}</div></div>',
		"commentsUrl": "<c:url value="/content/comments" />",
		"deleteCommentUrl": "<c:url value="/content/delete_comment"/>",
		"loadPlaylistsUrl": "<c:url value="/content/playlists"/>",
		"addToPlaylistUrl": "<c:url value="/content/add_to_playlist" />",
		"totalRating": <c:out value="${totalRating}"/>,
		"embedCodeUrl": "<c:url value="/content/embedcode"/>"
};


$(document).ready(function () {
	initcl(params);
});
</script>

	<jsp:include page="/WEB-INF/views/components/header.jsp" />
	
	<jsp:include page="/WEB-INF/views/components/search_form.jsp" />

	
	<div style="min-height: 400px;">
		<div id="embedcodearea" style="float:left;width:  700px;">
		</div>
		<ul class="list-group" style="float: left;width: 400px;">
			<li class="list-group-item">${artistName}<span class="badge"><spring:message code="label.general.artist"/></span></li>
			<li class="list-group-item">${name}<span class="badge"><spring:message code="label.general.name"/></span></li>
			<li class="list-group-item">${duration}<span class="badge"><spring:message code="label.general.duration"/></span></li>
			<li class="list-group-item">${genreName}<span class="badge"><spring:message code="label.general.genre"/></span></li>
			<li class="list-group-item">${publishDate}<span class="badge"><spring:message code="label.general.publishDate"/></span></li>
		</ul>
		<div style="float: right;">
			<span>
				<input type="checkbox" id="favorite" name="favorite" <c:if test="${favorite}">checked="checked"</c:if> />
				<spring:message code="label.general.favorite" />
			</span><br>
			
			<div style="display: inline;">
				<div id="rate"></div>
			</div>
			<input type="button"  class="btn btn-primary" id="atpl_btn" value="<spring:message code="content.listen.add_to_playlist"/>"/>
			<div class="list-group playlist-list" id="atpl_window">
				<ul id="playlists">
				</ul>
				<ul>
				<li class="list-group-item" style="width: 300px">
					<div class="input-group">
						<span class="input-group-addon">
							<input type="radio" name="playlist" value="-1" style="float: left"/>
						</span>
						<input type="text" class="form-control"   id="npl_title"  value="<spring:message code="content.listen.playlist.new"/>"/>
					</div>
				</li>
				</ul>
				<ul>
				<li class="list-group-item">
					<input type="button"  class="btn btn-primary" id="atpl_cancel" value="<spring:message code="label.general.cancel" />" />
					<input type="button"  class="btn btn-primary" id="atpl_save" value="<spring:message code="label.general.submit" />" />
				</li>
				</ul>
			</div>
			<div><span class="badge"><spring:message code="label.general.lyrics"/></span><pre>${lyrics}</pre></div>
		</div>
		
	</div>
	
	
	<div class="jumbotron" style="width: 600px; float: left">
		<div id="comment">
			<textarea id="commentarea" class="form-control" maxlength="254" rows="4" cols="50" ></textarea>
			<a class="btn btn-primary" id="addcomment" href="/" ><spring:message code="content.listen.comment"/></a>
		</div>
	
		<div id="commentlist">
		</div>
	</div>

</div>
</body>
</html>