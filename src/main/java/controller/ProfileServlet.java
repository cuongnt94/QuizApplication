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

import DAO.AccountDAO;
import DAO.DaoException;
import DAO.QuizDAO;
import hibernateDomain.Account;
import hibernateDomain.Category;
import hibernateDomain.Feedback;
import hibernateDomain.QuestCatResult;
import hibernateDomain.Question;
import hibernateDomain.Submission;

//@WebServlet(name= "register", urlPatterns = {"/user"}, loadOnStartup = 1)
@WebServlet("/exam")
public class ProfileServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuizDAO quizDAO;
	private AccountDAO accountDAO;
	
	public ProfileServlet()
	{
		quizDAO = new QuizDAO();
		accountDAO = new AccountDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// get the old session. If there is none, create a new one.
		HttpSession session = request.getSession(false);
		if (session == null) 
		{
			session = request.getSession(true);
		}
		
		// admin part
		/*String admin = request.getParameter("Admin");
		//System.out.println("dis me");
		//System.out.println("Value of admin:" + admin);
		if(admin != null)
		{
			//1. load data from submission_data
			List<Submission> submissionList = accountDAO.getSubmissionList();
			session.setAttribute("submissionList", submissionList);
			
			List<Feedback> feedbackList = accountDAO.getFeedbackList();
			session.setAttribute("feedbackList", feedbackList);
			
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}*/
		
		/* 
		 * user part
		*/
		
		// Give feedback
		
		
		
		// Choosing exam
		String categoryName = request.getParameter("test");
		
		System.out.println("Exam type: " + categoryName);
		
		
		
		if ("java".equalsIgnoreCase(categoryName)) 
		{
			List<QuestCatResult> listQuestion = quizDAO.getQuestionList("Java");
			session.setAttribute("quiz", listQuestion);
			session.setAttribute("categoryID", 1);
			session.setAttribute("index", 0);
			session.setAttribute("time", 60*15);
			session.setAttribute("size", 10);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			String startTime = dtf.format(now);
			session.setAttribute("startTime", startTime);
			
			Category category = new Category(1, "Java");
			session.setAttribute("category", category);
			request.getRequestDispatcher("quiz.jsp").forward(request, response);
		} 
		else if ("game".equalsIgnoreCase(categoryName))
		{
			

		}
		else {
			throw new NullPointerException("Exception occurred during processing!");
		}
	}
	
	/*
	 * Handle the feedback submit
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		if (session == null) 
		{
			session = request.getSession(true);
		}
		
		String feedbackText = request.getParameter("feedbackText");
		
		String feedbackButton = request.getParameter("submitFeedback");
		
		if(feedbackButton != null)
		{
			Feedback feedback = new Feedback(feedbackText);
			Account account = (Account)session.getAttribute("account");
			feedback.setAccount(account);
			accountDAO.submitFeedback(feedback);
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
	}
}
