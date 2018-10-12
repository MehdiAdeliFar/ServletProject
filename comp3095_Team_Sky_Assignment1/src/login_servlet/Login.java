/*
 ********************************************************************************************************************
 * Project: comp3095_Team_Sky_Assignment1
 * Assignment: assignment 1
 * Author(s): Leila Jalali Abyaneh - Sogol Ganji Haghighi
 * Student Number: 101088286 - 100902745
 * Date: October/04/2018
 * Description: The helper class for the project
 ********************************************************************************************************************
*/
package login_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Cookie userName = new Cookie("userName", request.getParameter("username"));
		userName.setMaxAge(60*60*24);
		response.addCookie(userName);
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();		
		
		String docType = "<!DOCTYPE html>";
		String header = "<html><head><title>Insert title here</title>"
						+ "<script src='https://www.google.com/recaptcha/api.js'></script>"
						+ "</head></html><body><h1>LOGIN</h1>";		
		String bodeyTags ="<filedset>"
							+ "<form action='Login' method='POST'>"
								+ "<lable for='username'>Username:</lable>"
									+ "<input type='text' name='username'><br>"
								+ "<lable for='password'>Password:</lable>"
									+ "<input type='text' name='password'><br>"
								+ "<div class=\"g-recaptcha\" data-sitekey=\"6LdUknMUAAAAAMbLTVnxfmyvpVnr-sAcWQ5UIN8P\"></div>"
									+ "<input type='submit' value='Login'>"
									+ "<input type='submit' value='Register' formaction='Register'><br>"
									+ "<a href='./test.html'>Forgot your password?</a>"
							+ "</form>"
						+ "</filedset>";		
		String footer = "</body></html>";	
		String htmlTags = docType + header + bodeyTags + footer;
		

			pw.println(htmlTags);
			
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminUserName = "admin@isp.net";
		String adminPassword = "P@ssword1";

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify =helper.Helper.verify(gRecaptchaResponse);
		PrintWriter out = response.getWriter();
//		if (verify) {
//			out.println("<font color=red>Either user name or password is wrong.</font>");
//		} else {
//			out.println("<font color=red>You missed the Captcha.</font>");
//		}
		if(helper.Helper.isEmty(username) && helper.Helper.isEmty(password)) {
			if(username.equals(adminUserName) && password.equals(adminPassword)) {
				response.sendRedirect("index.html");
			}
		}
	}

}
