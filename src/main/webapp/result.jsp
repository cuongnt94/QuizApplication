<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<title>Result</title>
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


<form action="result_control" method="get">
	 
	<c:out value = "${sessionScope.grade}" /> <br>
	<c:forEach items="${sessionScope.quiz}" var="question" varStatus="outer">
		<c:out value ="${outer.count}"/>
		<c:out value ="${question.questionText}"/> 
		<c:choose>
			<c:when test ="${question.userSelection == question.correctSelection}">
				<span style="font-size:11pt"> 	&#10004;  </span>
			</c:when>
			<c:otherwise>
				<span style="font-size:11pt"> 	&cross;  </span>
			</c:otherwise>
		</c:choose>
		<br>
		<c:forEach items="${question.choice}" var="option" varStatus="inner">
			<c:choose>
			<c:when test = "${question.userSelection == inner.index}">
				<input type="radio" name="${outer.index}" value="${inner.index}" checked="checked"> ${option}  <br/>
			</c:when>
			<c:when test = "${question.userSelection != inner.index}">
				<input type="radio" name="${outer.index}" value="${inner.index}" > ${option}  <br/>
			</c:when>
			</c:choose>
		</c:forEach>
	</c:forEach> 
	
	<input type="submit" value="Return to user page" name = "btnSubmit">
</form>


</body>
</html>