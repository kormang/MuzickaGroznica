<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.base.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.arctic.css"/>" type="text/css" />

 <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.js" />"></script>

 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcore.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxrating.js"/>"></script>


 <script type="text/javascript" src="<c:url value="/resources/js/content/listen.js" />"></script>

</head>
<body>

<script type="text/javascript">

var mcid = ${musicContentId};
var favoriteUrl = "<c:url value="/content/favorite" />";
var rateUrl = "<c:url value="/content/rate" />";
var rateValue = <c:choose><c:when test="${not empty rateValue}">${rateValue}</c:when><c:otherwise>null</c:otherwise></c:choose>;
var favorite = ${favorite};
var addCommentUrl = "<c:url value="/content/add_comment" />";
var commentTemplate = '<div style="border: 2px solid blue; display: inline-block;"><span style="float: left;">${user.username}</span><div>{{COMMENT_TEXT}}</div></div>';
var commentsUrl  = "<c:url value="/content/comments" />";
var deleteCommentUrl = "<c:url value="/content/delete_comment"/>";
var loadPlaylistsUrl = "<c:url value="/content/playlists"/>";
var addToPlaylistUrl = "<c:url value="/content/add_to_playlist" />";
var totalRating = ${totalRating};

$(document).ready(function () {
	var params = {
			"favoriteUrl" : favoriteUrl,
			"mcid" : mcid,
			"rateUrl" : rateUrl,
			"rateValue" : rateValue,
			"favorite" : favorite,
			"addCommentUrl" : addCommentUrl,
			"commentTemplate": commentTemplate,
			"commentsUrl": commentsUrl,
			"deleteCommentUrl": deleteCommentUrl,
			"loadPlaylistsUrl": loadPlaylistsUrl,
			"addToPlaylistUrl": addToPlaylistUrl,
			"totalRating": totalRating
	};

	initcl(params);
});
</script>

	${embeddCode}

	<div>
		<div>
			<span>
				<input type="checkbox" id="favorite" name="favorite" <c:if test="${favorite}">checked="checked"</c:if> />
				<spring:message code="label.general.favorite" />
			</span>
			<div style="display: inline;">
				<div id="rate"></div>
			</div>
		</div>
	
		<div>
			<span>${artistName}</span>
			<span>${name}</span>
			<span>${duration}</span>
			<span>${genreName}</span>
			<span>${publishDate}</span>
		</div>
		<div>
			<input type="button" id="atpl_btn" value="<spring:message code="content.listen.add_to_playlist"/>"/>
			<div style="background-color: grey; position: absolute; top: 45%; left: 45%;" id="atpl_window">
				<div id="playlists">
				</div>
				<div><input type="radio" name="playlist" value="-1" /><input type="text" id="npl_title" value="<spring:message code="content.listen.playlist.new"/>"/></div>
				<div>
					<input type="button" id="atpl_cancel" value="<spring:message code="label.general.cancel" />" />
					<input type="button" id="atpl_save" value="<spring:message code="label.general.submit" />" />
				</div>
			</div>
		</div>
		<div>
			${lyrics}
		</div>
	</div>
	
	<div id="comment">
		<textarea id="commentarea" maxlength="254" rows="4" cols="50" ></textarea>
		<a id="addcomment" href="/"><spring:message code="content.listen.comment"/></a>
	</div>
	
	<div id="commentlist">
	</div>

</body>
</html>