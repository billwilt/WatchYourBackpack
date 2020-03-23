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
	<div>
		<form method="post">
			<p>
				<label>Player Name</label><input type="text" name="name">
			<p>
				<label>Player Description</label><input type="text" maxlength="255" name="description">
			<p>
				<label>Type</label> <select name="type">
					<option value="1">Fighter</option>
					<option value="2">Pyro</option>
					<option value="3">Resourceful</option>
				</select>
			</p>
			<input type="hidden" value=10 name="money">
			<button>Add Player</button>
		</form>
	</div>
</body>
</html>