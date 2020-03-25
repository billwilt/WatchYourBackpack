<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<head>
<link href="/style.css" rel="stylesheet" />

<meta charset="ISO-8859-1">
<title>Are you sure about that?</title>
</head>
<body>
	<div class="confirmCube">
		<h2>You: ${gameStatus.mainPlayer.name}</h2>
		<h6>Attack: ${gameStatus.mainPlayer.getAttack()}</h6>
		<h6>Fire: ${gameStatus.mainPlayer.getFire()}</h6>
		<h6>Resourcefulness: ${gameStatus.mainPlayer.getResourcefulness()}</h6>
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
		<h5>Weather: ${gameStatus.weather.getSummary()}</h5>
		<h6>Total Cost: $${totalCost}</h6>
		<h6>Sleeping: ${sleeping}</h6>
		<h6>Wallet after trip: $${walletAfter}</h6>
	</div>
	
	<a href="/day1" class="startButton">PLAY THE GAME</a>
</body>
</html>