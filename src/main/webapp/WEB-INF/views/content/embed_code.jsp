<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:choose>
	<c:when test="${contentType == 0}">
	
		<div id="player_holder" style="display: inline-block; width: 500px;">
		<img id="eqvimg" src="<c:url value="/images/audio-player-header.jpg"/>" style="width: 500px"></img>
		<audio controls autoplay id="player" style="display: block; margin-left: auto; margin-right: auto; margin: 0 auto; width: 100%">
			<source src="${filePath}">
		</audio>
		</div>
	
	</c:when>
	
	
	<c:when test="${contentType == 1}">
		
		
		<iframe width="560" height="315" src="https://www.youtube.com/embed/${videoId}?autoplay=1" frameborder="0" allowfullscreen>
		</iframe>
		
		
	</c:when>
	
	<c:when test="${contentType == 2}">
	
		<iframe width="80%" height="225" scrolling="no" frameborder="no" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/${trackId}&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true">
		</iframe>
	
	</c:when>
	
	
</c:choose>

