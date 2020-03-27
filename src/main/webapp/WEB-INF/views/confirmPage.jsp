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
<link href="/style.css" rel="stylesheet" />
<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<title>Watch your Back!pack</title>
</head>
<body>

	<div class="background3">
	
		<div class="left-space">
			<h5>Your Character:</h5>
			<h3>${gameStatus.mainPlayer.name}</h3>
			<h5>Attack: ${gameStatus.mainPlayer.getAttack()}</h5>
			<h5>Fire: ${gameStatus.mainPlayer.getFire()}</h5>
			<h5>Resourcefulness:
				${gameStatus.mainPlayer.getResourcefulness()}</h5>
			<h5>Wallet before trip: $${gameStatus.mainPlayer.getMoney()}</h5>
		</div>
		
		<div class="center-space">
			<h2>Your Friend: ${gameStatus.partner.getName()}</h2>
			<h5>Attack: ${gameStatus.partner.getAttack()}</h5>
			<h5>Fire: ${gameStatus.partner.getFire()}</h5>
			<h5>Resourcefulness: ${gameStatus.partner.getResourcefulness()}</h5>
		</div>
		
		<div class="right-space">
			<h2>${gameStatus.park.getName()}</h2>
			<h5>Trip Length: ${ maxDays } Days</h5>
			<h5>Weather: ${gameStatus.weather.getSummary()}</h5>
			<h5>Total Cost: $${totalCost}</h5>
			<h5>Sleeping: ${sleeping}</h5>
			<h5>Wallet after trip: $${walletAfter}</h5>
		</div>

		<a href="/genericBeastDay" class="start-button-confirm">PLAY THE GAME</a>
	</div>
</body>
</html>