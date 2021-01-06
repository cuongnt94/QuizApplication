package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.QuizDAO;
import hibernateDomain.Category;
import hibernateDomain.Question;

@WebServlet("/admin_control")
public class AdminServlet extends HttpServlet{
	private QuizDAO quizDAO;
	public AdminServlet()
	{
		quizDAO = new QuizDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if (session == null) 
		{
			session = request.getSession(true);
		}
		
		String categoryID = request.getParameter("categoryOption");
		String questionText = request.getParameter("questionText");
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		String correctSelection = request.getParameter("correctOption");
	
		String[] choice = new String[] {option1, option2, option3, option4};
		quizDAO.addQuestion(Integer.parseInt(categoryID), questionText, Integer.parseInt(correctSelection), choice);
		request.getRequestDispatcher("admin_add_question.jsp").forward(request, response);

	}
}
