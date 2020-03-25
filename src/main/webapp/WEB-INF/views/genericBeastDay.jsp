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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
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