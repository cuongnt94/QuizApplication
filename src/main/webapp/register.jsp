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
<title>Quiz</title>
</head>
<body>

<form action = "signup_control" method="post">

<center>
<h1> Welcome to Quiz Application. This is a register page</h1></center>

<br><br>


<center>
User Name : <input type = "text" name = "UserName"/>
</center>

<center>
Password: <input type = "text" name = "Password"/>
</center>

<center>
Date of Birth: <input type = "text" name = "DoB"/>
</center>

<center>
Phone number <input type = "text" name = "Phone"/>
</center>

<center>
Email: <input type = "text" name = "Email"/>
</center>

<center>
First Name : <input type = "text" name = "FirstName"/>
</center>

<center>
Last Name : <input type = "text" name = "LastName"/>
</center>

<center>
<input type = "submit" value = "register"/>
</center>

</form>
</body>