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
	<form action="/confirmSettings" method="post">

		<div class="center">

			<h2>Current Settings: ${gameStatus.park.getName()}</h2>
			<h3>You Are: ${gameStatus.mainPlayer.getName()}</h3>
			<h4>Current $$$: ${gameStatus.mainPlayer.getMoney()}</h4>
			<h5>Current Weather: ${gameStatus.weather.getSummary()}</h5>
			<h5>Current Temp: ${gameStatus.weather.getTemperature()}</h5>

		</div>

		<div class="center">
			<table style="width: 100%">
				<tr>
					<td align="center" class="column">
						<div class="form-check disabled">
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
					</td>


					<td align="center" class="column">
						<div>
							<!--  	<form action="" method="post" class="card1"> -->
							<div>
							<h5>If you don't have shelter, mother nature will smell your fear</h5>
								<div class="parkChoice">

									<input type="radio" name="price" value=0 checked="checked" > <label>In
										the Leaves</label>
									<p>Price: FREE</p>
								</div>

								<div class="parkChoice">
									<input 
										<c:if test="${ gameStatus.mainPlayer.money lt 10 }">
										disabled 
										</c:if>
									type="radio" name="price" value=10> <label>Tent</label>
									<c:if test="${ gameStatus.mainPlayer.money lt 10 }">
											<p class="expensive"> You cannot afford this </p>
											</c:if>
									<p>Price: $10</p>
								</div>
								<c:if test="${ park.rvOption }">
									<div class="parkChoice" >
										<input 
											<c:if test="${ gameStatus.mainPlayer.money lt 20 }">
											disabled
											</c:if>
										type="radio" name="price" value=20> <label>RV</label>
										<c:if test="${ gameStatus.mainPlayer.money lt 20 }">
											<p class="expensive"> You cannot afford this </p>
											</c:if><p>Price: $20</p>
									</div>
								</c:if>

							</div>
						</div> <!-- </form> -->
					</td>

					<!-- <form action="" method="post" class="card1"> -->
					<td class="grayColor">
						<div>
							<!-- 	<label for="items">Choose three (3) items:</label>
						</div>
						<select id="items" name="items" size="9" multiple>
							<option value="matches">Matches</option>
							<option value="flintSteel">Flint &amp; Steel</option>
							<option value="wetFireStarterCube">Wet Fire Starter Cube</option>
							<option value="swissArmyKnife">Swiss Army Knife</option>
							<option value="machete">Machete</option>
							<option value="bearSpray">Bear Spray</option>
							<option value="mapCompass">Map &amp; Compass</option>
							<option value="rope">Rope</option>
							<option value="tarp">Tarp</option>
						</select><br> <br>
						-->
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
					</td>
				</tr>

			</table>
	</form>

</body>
</html>