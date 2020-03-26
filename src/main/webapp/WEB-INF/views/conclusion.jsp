<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Watch Your Backpack: Conclusion - Will you be going on
	any additional camping trips?</title>
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
</head>
<body>

	<h1>While hiking out you found some money on the ground, turned
		out to be $${moneyFound}</h1>

	<!-- end of game stats  -->
	<div class="confirmCube">
		<h2>You: ${gameStatus.mainPlayer.getName()}</h2>
		<h6>Attack: ${gameStatus.mainPlayer.getAttack()}</h6>
		<h6>Fire: ${gameStatus.mainPlayer.getFire()}</h6>
		<h6>Resourcefulness:
			${gameStatus.mainPlayer.getResourcefulness()}</h6>
		<h6>Wallet after trip: $${gameStatus.mainPlayer.getMoney()}</h6>
	</div>

	<c:choose>
		<c:when
			test="${ gameStatus.mainPlayer.getAttack() + gameStatus.mainPlayer.getFire() + gameStatus.mainPlayer.getResourcefulness() lt 9 }">

			<h1>You get to level up one skill for making it out, what do you
				want to increase?</h1>
				<h5>Your total skill count maxes out at 9, choose wisely.</h5>
			<div class="confirmCube">
				<form action="/backHome" method="post">
					<p>
						<label>Add to Attack</label><input type="radio" name="skill"
							value="1">
					</p>
					<p>
						<label>Add to Fire</label><input type="radio" name="skill"
							value="2">
					</p>
					<p>
						<label>Add to Resourcefulness</label><input type="radio"
							name="skill" value="3">
					</p>
					<button>THIS IS THE SKILL I WANT</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<h1>Your skills are maxed out YO! Getting out alive is its own reward!</h1>
			<a class="startButton" href="/index">Home</a>
		</c:otherwise>
	</c:choose>

</body>
</html>