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
		<h2>You: ${player1.getName()}</h2>
		<h6>Attack: ${player1.getAttack()}</h6>
		<h6>Fire: ${player1.getFire()}</h6>
		<h6>Resourcefulness: ${player1.getResourcefulness()}</h6>
		<h6>Wallet before trip: $${player1.getMoney()}</h6>
	</div>
	<div class="confirmCube">
		<h2>Your Friend: ${player2.getName()}</h2>
		<h6>Attack: ${player2.getAttack()}</h6>
		<h6>Fire: ${player2.getFire()}</h6>
		<h6>Resourcefulness: ${player2.getResourcefulness()}</h6>
	</div>
	<div class="confirmCube">
		<h2>Park: ${park.getName()}</h2>
		<h5>Weather: ${currentWeather.getSummary()}</h5>
		<h6>Total Cost: $${totalCost}</h6>
		<h6>Sleeping: ${sleeping}</h6>
		<h6>Wallet after trip: $${walletAfter}</h6>
	</div>
</body>
</html>