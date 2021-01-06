<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<title>Admin page</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navigation</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="admin.jsp">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="admin_add_question.jsp">Add question</a>
      
    </div>
  </div>
</nav>



<form>
	<table class="table">
	<tr>
		<th> Submission ID </th>
		<th> Account ID </th>
		<th> CategoryID </th>
		<th> Start Time </th>
		<th> End Time </th>
		<th> Grade </th>
	</tr>
	
	<c:forEach items = "${sessionScope.submissionList}" var = "submission_row">
		<tr>
			<td> <c:out value ="${submission_row.submissionID}"/> </td> 
			<td> <c:out value ="${submission_row.account.accountID}"/> </td>
			<td> <c:out value ="${submission_row.category.categoryID}"/> </td>
			<td> <c:out value ="${submission_row.startTime}"/> </td>
			<td> <c:out value ="${submission_row.endTime}"/> </td>
			<td> <c:out value ="${submission_row.grade}"/> </td>
		</tr>	
	</c:forEach>
	
	</table>
	
	<hr>
	<table class="table">
	<tr>
		<th> Feedback ID </th>
		<th> Feedback Text </th>
	</tr>
	<c:forEach items = "${sessionScope.feedbackList}" var = "feedback_row">
		<tr>
			<td> <c:out value ="${feedback_row.feedbackID}"/> </td>
			<td> <c:out value ="${feedback_row.feedbackText}"/> </td>		
		</tr>	
	</c:forEach>
	</table>
</form>
<div>
	
</div>

</body>

</html>