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
</head>>
<body>
	<table class="stats-table">
		<tr>
			<td>
				<h5>Team: ${gameStatus.mainPlayer.getName()} & ${gameStatus.partner.getName()}</h5>
				<h5>Day : ${dayCount}</h5>
				<h5>Day of Departure : ${maxDays}</h5>
			</td>
			<td>
				<h5>Team Attack: ${gameStatus.getTotalAttack()}</h5>
				<h5>Team Fire: ${gameStatus.getTotalFire()}</h5>
				<h5>Team Resourcefulness:
					${gameStatus.getTotalResourcefulness()}</h5>
				<h6>Team Health: ${gameStatus.getHealth()}</h6>
			<td>
		</tr>
	</table>
	<div>
		<h1>You were attacked by: ${event.getName()}</h1>
		<h4>${event.getDescription()}</h4>
	</div>
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

</body>
</html>