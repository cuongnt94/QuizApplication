package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.QuizDAO;
import hibernateDomain.Question;
import hibernateDomain.Submission;
import hibernateDomain.Account;
import hibernateDomain.Category;
import hibernateDomain.QuestCatResult;


@WebServlet("/quiz_control")
public class QuizServlet extends HttpServlet{
	//private JDBC database;
	private QuizDAO quizDAO;
	public QuizServlet() {
		quizDAO = new QuizDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if (session == null) 
		{
			session = request.getSession(true);
		}
		
		String answer = request.getParameter("answer");
		
		if(answer != null)
		{
			if(answer.equals("0"))
			{
				session.setAttribute("userSelection", 0);
			}
			else if(answer.equals("1"))
			{
				session.setAttribute("userSelection", 1);
			}
			else if(answer.equals("2"))
			{
				session.setAttribute("userSelection", 2);
			}
			else if(answer.equals("3"))
			{
				session.setAttribute("userSelection", 3);
			}
		}
		else
		{
			answer = "-1";
			session.setAttribute("userSelection", -1);
		}
		
		
		
		String previous = request.getParameter("Previous");
		String next = request.getParameter("Next");
		
		if(previous != null)
		{
			//1. save the userSelection into List<Question>
			int index = (int) session.getAttribute("index");
			List<QuestCatResult> list = (List<QuestCatResult>) session.getAttribute("quiz");
			QuestCatResult question = list.get(index);
			question.setUserSelection(Integer.parseInt(answer));
			list.set(index, question);
			
			session.setAttribute("quiz", list);
			
			//2. get the current time
			String currentTime = request.getParameter("timeInSec");
			session.setAttribute("time", Integer.parseInt(currentTime));
			
			//3. decrement the index 
			session.setAttribute("index", index-1);
			
			//4. get the userSelection for the next/previous question
			QuestCatResult questionNext = list.get(index-1);
			int userSelection = questionNext.getUserSelection();
			session.setAttribute("userSelection", userSelection);
			
			request.getRequestDispatcher("quiz.jsp").forward(request, response);
		}
		
		if(next != null)
		{
			//1. save the userSelection into List<Question>
			int index = (int) session.getAttribute("index");
			List<QuestCatResult> list = (List<QuestCatResult>) session.getAttribute("quiz");
			QuestCatResult question = list.get(index);
			question.setUserSelection(Integer.parseInt(answer));
			list.set(index, question);
			
			session.setAttribute("quiz", list);
			
			//2. get the current time
			String currentTime = request.getParameter("timeInSec");
			session.setAttribute("time", Integer.parseInt(currentTime));
			
			//3. decrement the index 
			session.setAttribute("index", index+1);
			
			//4. get the userSelection for the next/previous question
			QuestCatResult questionNext = list.get(index+1);
			int userSelection = questionNext.getUserSelection();
			session.setAttribute("userSelection", userSelection);
			
			request.getRequestDispatcher("quiz.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if (session == null) 
		{
			session = request.getSession(true);
		}
		
		String answer = request.getParameter("answer");
		
		if(answer != null)
		{
			if(answer.equals("0"))
			{
				session.setAttribute("userSelection", 0);
			}
			else if(answer.equals("1"))
			{
				session.setAttribute("userSelection", 1);
			}
			else if(answer.equals("2"))
			{
				session.setAttribute("userSelection", 2);
			}
			else if(answer.equals("3"))
			{
				session.setAttribute("userSelection", 3);
			}
		}
		else
		{
			answer = "-1";
			session.setAttribute("userSelection", -1);
		}
		
		//get the current time
		String currentTime = request.getParameter("timeInSec");
		
		
		String submit = request.getParameter("btnSubmit");
		if(submit != null || currentTime == null)
		{
			//1. save the userSelection into List<Question>
			int index = (int) session.getAttribute("index");
			List<QuestCatResult> list = (List<QuestCatResult>) session.getAttribute("quiz");
			QuestCatResult question = list.get(index);
			question.setUserSelection(Integer.parseInt(answer));
			list.set(index, question);
			session.setAttribute("quiz", list);
			
			
			//2. Calculate the score 
			//String quizID = (String)session.getAttribute("quizID");
			int categoryID = (int) session.getAttribute("categoryID");
			
			int size = (int) session.getAttribute("size");
			double grade = 0;
			List<QuestCatResult> newList = (List<QuestCatResult>) session.getAttribute("quiz");
			for(int i = 0; i < size; i++)
			{
				QuestCatResult q = newList.get(i);
				if(q.getUserSelection() == q.getCorrectSelection())
				{
					grade++;
				}
			}
			grade = grade * 100 / size;
			session.setAttribute("grade", grade);
			
			// get the endTime
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			String endTime = dtf.format(now);
			
			
			//3. submit the quiz to database
			String startTime = (String) session.getAttribute("startTime");
			
			Account account = (Account) session.getAttribute("account");
			Category category = (Category) session.getAttribute("category");
			Submission submission = new Submission(startTime, endTime, grade);
			submission.setGrade(grade);
			submission.setAccount(account);
			submission.setCategory(category);
			quizDAO.submit(submission);
			
			//4. show the result on result.jsp
			request.getRequestDispatcher("result.jsp").forward(request, response);
		}
	}
}
