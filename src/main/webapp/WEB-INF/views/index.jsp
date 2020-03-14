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
				<select name="user" class="custom-select" required>
					<option value="NO">Player Choices</option>
					<option value="1">One</option>
					<option value="2">Two</option>
					<option value="3">Three</option>
				</select>
			</div>
		</div>

		<div class="form-check disabled inputForm-right">
			<h2>CHOOSE YOUR CAMPSITE</h2>
			<div class="parkChoice">
				<input type="radio" name="park" id="parkID" value="${isleRoyale}">
				<label class="choice">Isle Royale </label>
				<p>Current Weather: ${isleRoyaleWeather.getSummary()}</p>
				<p>Temperature: ${isleRoyaleWeather.getTemperature()} F</p>
			</div>

			<div class="parkChoice">
				<input type="radio" name="park" id="parkID" value="${yellowstone}">
				<label class="choice">Yellowstone </label>
				<p>Current Weather: ${yellowstoneWeather.getSummary()}</p>
				<p>Temperature: ${yellowstoneWeather.getTemperature()} F</p>
			</div>

			<div class="parkChoice">
				<input type="radio" name="park" id="parkID" value="${grandCanyon}">
				<label class="choice">Grand Canyon </label>
				<p>Current Weather: ${grandCanyonWeather.getSummary()}</p>
				<p>Temperature: ${grandCanyonWeather.getTemperature()} F</p>
			</div>
		</div>

		<button class="startButton">START GAME</button>
	</form>

</body>
</html>