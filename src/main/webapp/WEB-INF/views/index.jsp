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
<!-- Favicon It's a coffee cup right now. Change it to whatever you want-->
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
<body
	style="background-image: url('WatchYourBackpack-01.jpg'); background-repeat: no-repeat; background-size: 75% 76%;">

	<form action="/start" method="post">

		<div class="form-check disabled inputForm-left">
			<h2>CHOOSE YOUR PLAYER</h2>
			<div class="form-group">
				<select name="id" class="custom-select" required>
					<c:forEach items="${players}" var="player">
					
					
					
						
						<option value="${player.getId()}">${player.getName()} Attack: ${player.getAttack()} Fire: ${player.getFire()} Resourcefulness: ${player.getResourcefulness()}</option>
					
					
					
					
					</c:forEach>
					<!-- Attack: ${player.getAttack()} Fire: ${player.getFire()} Resourcefulness: ${player.getResourcefulness} --->
				</select>
				
				<a href="/newPlayer">Add New Player</a>
			</div>
		</div>

		<div class="form-check disabled inputForm-right">
			<h2>CHOOSE YOUR CAMPSITE</h2>
			<div class="parkChoice">
				<input type="radio" name="parkCode" id="parkID"
					value="${isleRoyale.getData().get(0).getParkCode()}"> <label
					class="choice">Isle Royale </label>
				<p>Current Weather: ${isleRoyaleWeather.getSummary()}</p>
				<p>Temperature: ${isleRoyaleWeather.getTemperature()} F</p>
			</div>

			<div class="parkChoice">
				<input type="radio" name="parkCode" id="parkID"
					value="${yellowstone.getData().get(0).getParkCode()}"> <label
					class="choice">Yellowstone </label>
				<p>Current Weather: ${yellowstoneWeather.getSummary()}</p>
				<p>Temperature: ${yellowstoneWeather.getTemperature()} F</p>
			</div>

			<div class="parkChoice">
				<input type="radio" name="parkCode" id="parkID"
					value="${grandCanyon.getData().get(0).getParkCode()}"> <label
					class="choice">Grand Canyon </label>
				<p>Current Weather: ${grandCanyonWeather.getSummary()}</p>
				<p>Temperature: ${grandCanyonWeather.getTemperature()} F</p>
			</div>
		</div>

		<button class="startButton">START GAME</button>
	</form>

</body>
</html>