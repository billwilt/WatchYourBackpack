<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<!-- Favicon-->
<link rel="icon" type="image/png" href="/backpack-01.png">
<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<!-- Bootswatch Theme Flatly. Grab a different one from https://www.bootstrapcdn.com/bootswatch/ if you want-->
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/flatly/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-yrfSO0DBjS56u5M+SjWTyAHujrkiYVtRYh2dtB3yLQtUz3bodOeialO59u5lUCFF"
	crossorigin="anonymous">
<!-- Your custom styles -->
<link href="/style.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/c66cb055e4.js"
	crossorigin="anonymous"></script>
<title>Watch your Back!pack</title>
</head>
<body>
	<div class="background4">
		<div class="left-space-tall-2up">
			<h5>${outcome.getDescription()}</h5>
			<c:choose>
				<c:when test="${outcome.getSurvived()}">
					<h5>You successfully went out for food!</h5>
				</c:when>
				<c:otherwise>
					<h5>You were unprepared to face Mother Nature's mighty wrath.</h5>

					<p>Would you like to try and phone a friend to see if they can
						help you out of this mess?</p>
					<a href="/phoneAFriend" type="button" class="btn startButton">
						<i class="fas fa-phone-volume" style="font-size: 2rem;"></i>  Phone a friend
					</a>
				</c:otherwise>
			</c:choose>
			<h6>${ message }</h6>

			<form action="/dayController" method="post">
				<button class="btn startButton">NEXT DAY</button>

			</form>
		</div>
		<div class="right-space-tall-2up">
			<img src="${event.eventImageUrl }">
		</div>
	</div>
</body>
</html>