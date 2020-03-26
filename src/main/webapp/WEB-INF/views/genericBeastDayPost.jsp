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
	<c:choose>
	<c:when test="${outcome.getSurvived()}">
	 <h1>Success!</h1>
	</c:when>
	<c:otherwise>
	<h1>Womp Womp!</h1>
	</c:otherwise>
	</c:choose>
	<h1>${outcome.getDescription()}</h1>
	<form action="/dayController" method="post">
	<button>NEXT DAY</button>
	</form>
</body>
</html>