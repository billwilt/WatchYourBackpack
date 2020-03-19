<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/style.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Watch Your Backpack: Conclusion - Will you be going on any additional camping trips?</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
</head>
<body>

	<!-- Results from 3 days  -->
	<div class="confirmCube">
		<h2>Results  </h2>
		<h6>Day 1: ${event1.getName()} </h6>
		<h6>Day 2: ${event2.getName()} </h6>
		<h6>Day 3: ${event3.getName()} </h6>
	</div>

	<!-- end of game stats  -->
	<div class="confirmCube">
		<h2>You: ${player1.getName()}</h2>
		<h6>Attack: ${player1.getAttack()}</h6>
		<h6>Fire: ${player1.getFire()}</h6>
		<h6>Resourcefulness: ${player1.getResourcefulness()}</h6>
		<h6>Wallet after trip: $${player1.getMoney()}</h6>
	</div>





</body>
</html>