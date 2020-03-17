<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<link href="/style.css" rel="stylesheet" />

<title>Insert title here</title>
</head>
<body>
<div class="confirmCube">
<h5>Team: ${player1.getName()} & ${player2.getName()}</h5>
<h5>Health: ${gameStatus.getHealth()}</h5>
<h5>Weather: ${gameStatus.getWeather().getSummary()}</h5>
<h6>Day 6</h6>
</div>
<div class="confirmCube">
<h5>Team Attack: ${gameStatus.getTotalAttack()}</h5>
<h5>Team Fire: ${gameStatus.getTotalFire()}</h5>
<h5>Team Resourcefulness: ${gameStatus.getTotalResourcefulness()}</h5>
</div>
</body>
</html>