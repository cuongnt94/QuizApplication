package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AccountDAO;
import hibernateStarter.ApplicationStarter;

@WebServlet("/result_control")
public class ResultServlet extends HttpServlet{
	public ResultServlet()
    {
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String submit = request.getParameter("btnSubmit");
		if(submit != null)
		{
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
	}
}
