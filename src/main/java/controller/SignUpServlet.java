package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;
import hibernateDomain.Account;

@WebServlet("/signup_control")
public class SignUpServlet extends HttpServlet{
	private AccountDAO accountDAO;
	public SignUpServlet()
	{
		accountDAO = new AccountDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null) {
			oldSession.invalidate();
		}
		// generate a new session
		HttpSession newSession = request.getSession(true);

		// setting session to expiry in 5 mins
		newSession.setMaxInactiveInterval(5 * 60);
		
		String userName = request.getParameter("UserName");
		
		String password = request.getParameter("Password");
		String birth = request.getParameter("DoB");
		String phone = request.getParameter("Phone");
		String email = request.getParameter("Email");
		String firstName = request.getParameter("FirstName");
		String lastName = request.getParameter("LastName");
		
		
		Account account = new Account(userName, password, email, phone,birth, firstName, lastName );
		accountDAO.addAccount(account);
		
		request.getRequestDispatcher("index.html").forward(request, response);
	}
}
