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

<title>Watch your Back!pack</title>
</head>
<body>
	<div class="confirmCube">
		<h2>You: ${gameStatus.mainPlayer.name}</h2>
		<h6>Attack: ${gameStatus.mainPlayer.getAttack()}</h6>
		<h6>Fire: ${gameStatus.mainPlayer.getFire()}</h6>
		<h6>Resourcefulness:
			${gameStatus.mainPlayer.getResourcefulness()}</h6>
		<h6>Wallet before trip: $${gameStatus.mainPlayer.getMoney()}</h6>
	</div>
	<div class="confirmCube">
		<h2>Your Friend: ${gameStatus.partner.getName()}</h2>
		<h6>Attack: ${gameStatus.partner.getAttack()}</h6>
		<h6>Fire: ${gameStatus.partner.getFire()}</h6>
		<h6>Resourcefulness: ${gameStatus.partner.getResourcefulness()}</h6>
	</div>
	<div class="confirmCube">
		<h2>Park: ${gameStatus.park.getName()}</h2>
		<h3>Trip Length: ${ maxDays }</h3>
		<h5>Weather: ${gameStatus.weather.getSummary()}</h5>
		<h6>Total Cost: $${totalCost}</h6>
		<h6>Sleeping: ${sleeping}</h6>
		<h6>Wallet after trip: $${walletAfter}</h6>
	</div>

	<a href="/genericBeastDay" class="startButton">PLAY THE GAME</a>
</body>
</html>