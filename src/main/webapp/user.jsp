<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Search Info</title>
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navigation</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="LogoutServlet">Log Out</a>
      
    </div>
  </div>
</nav>


	<div>
	<p>AccountID: <c:out value = "${sessionScope.accountID}" /></p>
	<p>UserName: <c:out value = "${sessionScope.userName}" /></p>
	<p>Password: <c:out value = "${sessionScope.password}" /></p>
	<p>Date of Birth: <c:out value = "${sessionScope.birth}" /></p>
	<p>Phone Number: <c:out value = "${sessionScope.phone}" /></p>
	<p>Email: <c:out value = "${sessionScope.email}" /></p>
	<p>FirstName: <c:out value = "${sessionScope.firstName}" /></p>
	<p>LastName: <c:out value = "${sessionScope.lastName}" /></p>
	
	
	</div>
	
	<hr>
	<br>
	<a href="exam?test=java"> Java Test </a> <br>
	<a href="exam?test=game"> Game Test </a>
	
	<form action = "exam" method = "post">
	<div>
	<p>Would you like to leave us a feedback?</p>
	<input type ="text" name = "feedbackText">
	<input type="submit" name = "submitFeedback">
	</div>
	</form>
 







</body>

</html>