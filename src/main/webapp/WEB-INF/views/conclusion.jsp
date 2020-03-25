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
	
	<h1>While hiking out you found some money on the ground, turned out to be $${moneyFound}</h1>
	
	<!-- end of game stats  -->
	<div class="confirmCube">
		<h2>You: ${gameStatus.mainPlayer.getName()}</h2>
		<h6>Attack: ${gameStatus.mainPlayer.getAttack()}</h6>
		<h6>Fire: ${gameStatus.mainPlayer.getFire()}</h6>
		<h6>Resourcefulness: ${gameStatus.mainPlayer.getResourcefulness()}</h6>
		<h6>Wallet after trip: $${gameStatus.mainPlayer.getMoney()}</h6>
	</div>
	<h1>You get to level up one skill for making it out, what do you want to increase?</h1>
	<div class="confirmCube">
	<form action="/backHome" method="post">
		<p><label>Add to Attack</label><input type="radio" name="skill" value="1"></p>
		<p><label>Add to Fire</label><input type="radio" name="skill" value="2"></p>
		<p><label>Add to Resourcefulness</label><input type="radio" name="skill" value="3"></p>
		<button>THIS IS THE SKILL I WANT</button>
	</form>
	</div>



</body>
</html>