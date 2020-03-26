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

		<div class="left-space-tall">


			<h5>${gameStatus.mainPlayer.getName()}</h5>
			<h5>${gameStatus.partner.getName()}</h5>
			<br>
			<span style="font-size: 2rem; color: red;"><i
				class="fas fa-heart"></i> ${gameStatus.getHealth()}</span> <br>
			<span style="font-size: 2rem; color: #585758;"><i
				class="far fa-calendar-alt"></i> ${dayCount}</span> <br>
			<span style="font-size: 1rem; color: #585758;"><i
				class="fas fa-calendar-alt"></i><i class="fas fa-sign-out-alt"></i></span><span
				style="font-size: 2rem; color: #585758;"> ${maxDays}</span> <br>
			<span style="font-size: 2rem; color: darkred;"><i
				class="fas fa-bomb"></i> ${gameStatus.getTotalAttack()}</span> <br>
			<span style="font-size: 2rem; color: darkorange;"><i
				class="fas fa-fire"></i> ${gameStatus.getTotalFire()}</span> <br>
			<span style="font-size: 2rem; color: darkblue;"><i
				class="fas fa-tools"></i> ${gameStatus.getTotalResourcefulness()}</span>



		</div>

		<div class="center-space-tall">

			<div>
				<h3>${event.getName()}</h3>

				<h5>${event.getDescription()}</h5>

				<div>
					<form method="post">
						<select name="choice">
							<option value="1">Fight it off</option>
							<option value="2">Light it on fire</option>
							<option value="3">Try to run away</option>
						</select>
						<button>Survive</button>
					</form>
				</div>
			</div>

		</div>

		<div class="right-space-tall">
		<img src="${event.eventImageUrl }"></div>
		

	</div>





</body>
</html>