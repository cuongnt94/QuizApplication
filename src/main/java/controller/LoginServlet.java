package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;
import DAO.QuizDAO;
import hibernateDomain.Feedback;
import hibernateDomain.Submission;
import hibernateStarter.ApplicationStarter;


@WebServlet("/login_control")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */

	private final String USERNAME = "admin";
    private final String PASSWORD = "password";
    ApplicationStarter applicationStarter;
    AccountDAO accountDAO;
    public LoginServlet()
    {
    	applicationStarter = new ApplicationStarter();
    	accountDAO = new AccountDAO();
    	System.out.println("Driver is loaded");
    }
    
    /*
     * Need to add more button function
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the old session and invalidate
		/*HttpSession oldSession = request.getSession(false);
		if (oldSession != null) {
			oldSession.invalidate();
		}
		// generate a new session
		HttpSession newSession = request.getSession(true);

		// setting session to expiry in 5 mins
		newSession.setMaxInactiveInterval(15 * 60);*/
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//request.getRequestDispatcher("user.jsp").forward(request, response);

		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		
		// Admin part
		
		/*String getUserName = (String) newSession.getAttribute("userNameInput");
		String getUserPassword = (String) newSession.getAttribute("userPasswordInput");*/
		System.out.println("userName: " + userName);
		System.out.println("password: " + password);
		if (userName.equals(USERNAME) && password.equals(PASSWORD))
		{
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			// generate a new session
			HttpSession newSession = request.getSession(true);

			// setting session to expiry in 5 mins
			newSession.setMaxInactiveInterval(5 * 60);
			newSession.setAttribute("admin", 1);
			
			
			List<Submission> submissionList = accountDAO.getSubmissionList();
			newSession.setAttribute("submissionList", submissionList);
			
			List<Feedback> feedbackList = accountDAO.getFeedbackList();
			newSession.setAttribute("feedbackList", feedbackList);
			
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
		//if (this.USERNAME.equals(username) && this.PASSWORD.equals(password))
		else if(applicationStarter.checkLogin(userName, password))
		{
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			// generate a new session
			HttpSession newSession = request.getSession(true);

			// setting session to expiry in 5 mins
			newSession.setMaxInactiveInterval(5 * 60);
			out.print("<b>Login Successfully</b>");
			out.print("<br>Welcome!");
			newSession.setAttribute("admin", 0);
			
			Map<String, String> data = applicationStarter.getUserData(userName);
			System.out.println("Dis me");
			/*if(data.isEmpty())
			{
				out.println("<h3>Incorrect user/password </h3>");
				request.getRequestDispatcher("login.html").include(request, response);
				return;
			}*/
			
			newSession.setAttribute("accountID", data.get("accountID"));
			newSession.setAttribute("userName", data.get("userName"));
			newSession.setAttribute("password", data.get("userPassword"));
			newSession.setAttribute("birth", data.get("birth"));
			newSession.setAttribute("phone", data.get("phone"));
			newSession.setAttribute("email", data.get("email"));
			newSession.setAttribute("firstName", data.get("firstName"));
			newSession.setAttribute("lastName", data.get("lastName"));
			
			//newSession.setAttribute("accountID", data.get("accountID"));
			newSession.setAttribute("account", accountDAO.getAccount(userName) );
			request.getRequestDispatcher("user.jsp").forward(request, response);
			
			
		}
		else {
			out.print("Incorrect user name or password!");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	}
	
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws  ServletException, IOException{
		System.out.println("doPost() method is called....");
		
		PrintWriter out = response.getWriter();
		out.println("<body>");
		
		String accountID = request.getParameter("accountID");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String email = request.getParameter("Email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		
		
		applicationStarter.addAccountData(userName, userPassword, email, birth, phone, firstName, lastName);
		out.println("<center> <h1> <bold> <font color=violet> Successfully Registered </font>  <bold> </h4></center>");
		response.sendRedirect("/QuizApplication/index.html");
		
		out.println("</body>");
	}*/

}
