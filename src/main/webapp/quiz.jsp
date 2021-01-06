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

<!--  <body onload=”examTimer”> -->
<body>


<form name = 'quizPro' action="quiz_control" method="get">
	<center>
	<p><c:out value = "${sessionScope.quiz[sessionScope.index].questionText}"/> </p>
	<c:forEach items="${sessionScope.quiz[sessionScope.index].choice}" var="option" varStatus="counter">
		<c:choose>
			<c:when test = "${sessionScope.quiz[sessionScope.index].userSelection == counter.index}">
				<input type="radio" name="answer" value="${counter.index}" checked="checked"> ${option}  <br/>
			</c:when>
			<c:when test = "${sessionScope.quiz[sessionScope.index].userSelection != counter.count}">
				<input type="radio" name="answer" value="${counter.index}" > ${option}  <br/>
			</c:when>
		</c:choose>
	</c:forEach>
	
	<c:if test="${sessionScope.index > 0}">
		<input type="submit" value="Previous" name = "Previous">
	</c:if>
	
	
	<c:if test="${sessionScope.index < size -1}">
		<input type="submit" value="Next" name = "Next">
	</c:if>
	</center>
	
	
	<input type="hidden" name="timeInSec" id = "timeInSec"/> 
	
	 
	
	
</form>

<form name = "submitQuiz" action = "quiz_control" method = "post">
	<center>
	<input type="submit" value="Submit" name = "btnSubmit" id ="inputSubmit">
	</center>
</form>


<br>

<div style="float:right;">Quiz will be closed in <span id="time"></span> minutes!</div>

<script>
var timeInSec = ${sessionScope.time};
function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    var warning = 60;
    var ok=false;
    console.log(timer);
    
    //console.log(form);
    function sub () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);
		
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
		
		
        display.textContent = minutes + ":" + seconds;
        
        /*if( timer <= warning && !ok ){
			alert('Time is running out.... less than 1 minute to go!');
			ok=true;
		}*/
        
        if (--timer < 0) {
            timer = duration;
            
            // submit the quiz
        
            //document.getElementById("submitQuiz").submit();
            //document.getElementById("inputSubmit").value = "Submit";
            document.forms["submitQuiz"].submit();
            
            
        }
        
        document.getElementById('timeInSec').value = timer;
        
    }
    sub();
    setInterval(sub, 1000);
}

window.onload = function () {
    //var fiveMinutes = 60 * 15,
        display = document.querySelector('#time');
    startTimer(timeInSec, display);
};

</script>

</body>
</html>