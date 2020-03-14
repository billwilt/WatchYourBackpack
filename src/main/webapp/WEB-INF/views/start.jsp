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

<title>New Game+</title>
</head>
<body>
	<div class="card1">
		<h2>Current Settings: ${park.getName()}</h2>
		<h3>You Are: ${user}</h3>
		<h5>Current Weather: ${currentWeather.getSummary()}</h5>
		<h5>Current Temp: ${currentWeather.getTemperature()}</h5>
	</div>
	
	<form action="" method="post" class="card1">
		<div>
			<div class="parkChoice">
				<input type="radio" name="price" value=0>
				<label class="choice">In the Leaves</label>
				<p>Price: FREE</p>
			</div>

			<div class="parkChoice">
				<input type="radio" name="price" value=10>
				<label class="choice">Tent</label>
				<p>Price: $10</p>
			</div>

			<div class="parkChoice">
				<input type="radio" name="price" value="${cost}">
				<label class="choice">Cabin</label>
				<p>Price: $${cost}</p>
			</div>
		</div>
	</form>
</body>
</html>