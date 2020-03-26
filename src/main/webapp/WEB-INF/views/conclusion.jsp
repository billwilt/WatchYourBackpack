<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/c66cb055e4.js"
	crossorigin="anonymous"></script>
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
	<div class="background1">

		<div class="left-short">

			<h5>While hiking out you found some money on the ground, turned
				out to be $${moneyFound}</h5>

			<!-- end of game stats  -->

			<h4>${gameStatus.mainPlayer.getName()} & ${gameStatus.partner.getName()}</h4>
			<br> <span style="font-size: 2rem; color: red;"><i
				class="fas fa-heart"></i> ${gameStatus.getHealth()}</span> <br> <span
				style="font-size: 2rem; color: darkgreen;"><i
				class="fas fa-dollar-sign"></i> ${gameStatus.mainPlayer.getMoney()}</span>
			<br> <span style="font-size: 1rem; color: #585758;"><i
				class="fas fa-calendar-alt"></i><i class="fas fa-sign-out-alt"></i></span><span
				style="font-size: 2rem; color: #585758;"> ${maxDays}</span> <br>

		</div>
		<div class="right-short">
			
			<c:choose>
				<c:when
					test="${ gameStatus.mainPlayer.getAttack() + gameStatus.mainPlayer.getFire() + gameStatus.mainPlayer.getResourcefulness() lt 9 }">

					<h5>You get to level up one skill for making it out alive!</h5>
					<h6>Your total skill count maxes out at 9, choose wisely.</h6>

					<form action="/backHome" method="post">
						
							<div class="container">
							<div class="row">
							<div class="col-sm-4"><span style="font-size: 2rem; color: darkred;"><i
								class="fas fa-bomb"></i> ${gameStatus.getTotalAttack()}</span>
							</div>
							<div class="col-sm-8"><br><input type="radio" name="skill" value="1"> <label>Attack</label>
							</div>
							</div>
							<div class="row">
							<div class="col-sm-4">
							<span style="font-size: 2rem; color: darkorange;"><i
								class="fas fa-fire"></i> ${gameStatus.getTotalFire()}</span>
							</div>
							<div class="col-sm-8">
							<br><input type="radio" name="skill" value="2"> <label>Fire</label>
							</div>
							</div>
							<div class="row">
							<div class="col-sm-4">
							<span style="font-size: 2rem; color: darkblue;"><i
								class="fas fa-tools"></i>
								${gameStatus.getTotalResourcefulness()}</span>
							</div>
							<div class="col-sm-8">
							<br><input type="radio" name="skill" value="3"> <label>Resourcefulness</label>
							</div>
							</div>
							</div>
							
						
						<button>THIS IS THE SKILL I WANT</button>
					</form>


				</c:when>
				<c:otherwise>
					<h5>Your skills are maxed out YO! Getting out alive is its own
						reward!</h5>
					<a class="startButton" href="/index">Home</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script src="/wyb.js"></script>
	<script>
		wonGame();
	</script>
</body>
</html>