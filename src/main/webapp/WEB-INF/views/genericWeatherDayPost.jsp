<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Did you win?: ${outcome.getSurvived()}</h1>
	<h1>${outcome.getDescription()}</h1>
	<c:if test="${outcome.getSurvived() eq 'false'}">
	<p>Would you like to try and phone a friend to see if they can help you out of this mess??</p>
	<a href="/phoneAFriend">Phone a friend</a>
	</c:if>
	
	<h6>${ message }</h6>
	
	<form action="/dayController" method="post">
	<button>NEXT DAY</button>
	</form>
</body>
</html>