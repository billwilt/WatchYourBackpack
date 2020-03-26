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
</head>
<body>
	<div class="background2">

		<form action="/confirmSettings" method="post">
		
				<div class="top-space">


					<h5>Park: ${gameStatus.park.getName()}</h5>

					<h5>You Are: ${gameStatus.mainPlayer.getName()}</h5>
					<h6>[Attack=${gameStatus.mainPlayer.getAttack()},
						Fire=${gameStatus.mainPlayer.getFire()},
						Resourcefulness=${gameStatus.mainPlayer.getResourcefulness()}]</h6>
					<h5>Current $$$: ${gameStatus.mainPlayer.getMoney()}</h5>
					<h5>Current Weather: ${gameStatus.weather.getSummary()}</h5>
					<h5>Current Temp: ${gameStatus.weather.getTemperature()}&#176</h5>

				</div>

			
			<div class="left-space">
	
				<h2>CHOOSE YOUR FRIEND</h2>
				<div class="form-group">
					<select name="id" class="custom-select" required>
						<c:forEach items="${availableTeam}" var="player">

							<option value="${player.getId()}">${player.getName()}
								[Attack=${player.getAttack()}, Fire=${player.getFire()},
								Resourcefulness=${player.getResourcefulness()}]</option>

						</c:forEach>
						<!-- Attack: ${player.getAttack()} Fire: ${player.getFire()} Resourcefulness: ${player.getResourcefulness} --->
					</select>
				</div>
			</div>

			<div class="center-space">


				<h5>If you don't have shelter, mother nature will smell your
					fear</h5>
				<div class="parkChoice">

					<input type="radio" name="price" value=0 checked="checked">
					<label>In the Leaves</label>
					<p>Price: FREE</p>
				</div>

				<div class="parkChoice">
					<input
						<c:if test="${ gameStatus.mainPlayer.money lt 10 }">
										disabled class="expensive-div"
										</c:if>
						type="radio" name="price" value=10> <label>Tent</label>
					<c:if test="${ gameStatus.mainPlayer.money lt 10 }">
						<p class="expensive">You cannot afford this</p>
					</c:if>
					<p>Price: $10</p>
				</div>

				<c:if test="${ gameStatus.park.rvOption }">
					<div class="parkChoice">
						<input
							<c:if test="${ gameStatus.mainPlayer.money lt 20 }">
											disabled class="expensive"
											</c:if>
							type="radio" name="price" value=20> <label>RV</label>
						<c:if test="${ gameStatus.mainPlayer.money lt 20 }">
							<p class="expensive">You cannot afford this</p>
						</c:if>
						<p>Price: $20</p>
					</div>
				</c:if>

			</div>
	</div>


	<div class="right-space"> 

		<h4>Choose Three (3) items:</h4>
		<p>
			<select id="item1" name="item1Id" required>
				<c:forEach items="${items}" var="item">
					<option value="${item.getId()}">${item.getItemName()}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<select id="item2" name="item2Id" required>
				<c:forEach items="${items}" var="item">
					<option value="${item.getId()}">${item.getItemName()}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<select id="item3" name="item3Id" required>
				<c:forEach items="${items}" var="item">
					<option value="${item.getId()}">${item.getItemName()}</option>
				</c:forEach>
			</select>
		</p>

		<button>Confirm</button>

	</div>
</div>
	</form>

</body>
</html>