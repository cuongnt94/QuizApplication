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
      <a class="nav-item nav-link" href="admin_add_question.jsp">Log Out</a>
      
    </div>
  </div>
</nav>

<form action = "admin_control" method ="post">
	
	<center>
	<label>Category: </label>
        <select name="categoryOption" id = "categoryOption">
               <option selected = "selected" value = "1">Java</option>
               <option value = "2">HTML</option>
               <option value = "3">JavaScript</option>
               <option value = "4">SpringBoot</option>
               <option value = "5">C++</option>
               <option value = "6">SQL</option>
        </select>

	</center>
	
	<center>
	Question Text : <input type = "text" name = "questionText"/>
	</center>
	
	<center>
	Option 1: <input type = "text" name = "option1"/>
	</center>
	
	<center>
	Option 2: <input type = "text" name = "option2"/>
	</center>
	
	<center>
	Option 3: <input type = "text" name = "option3"/>
	</center>
	
	<center>
	Option 4: <input type = "text" name = "option4"/>
	</center>
	
	<center>
	<label>Correct selection: </label>
        <select name ="correctOption"  id = "correctOption">
               <option selected = "selected" value = "0">Option 1</option>
               <option value = "1">Option 2</option>
               <option value = "2">Option 3</option>
               <option value = "3">Option 4</option>
        </select>
	</center>
	
	
	
	<center>
	<input type = "submit" value = "register"/>
	</center>	
</form>


</body>

</html>