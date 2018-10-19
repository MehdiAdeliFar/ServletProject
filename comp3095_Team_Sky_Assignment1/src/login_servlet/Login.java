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

import helper.DataBaseHelper;
import model.UserModel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		if (request.getParameter("logout") != null) {
			request.getSession().removeAttribute("username");

			response.sendRedirect("login.jsp");
			request.getSession().invalidate();

			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminUserName = "admin@isp.net";
		String adminPassword = "P@ssword1";
		
		if(request.getParameter("gotoRegister")!=null && request.getParameter("gotoRegister").equals("Register")) {
			response.sendRedirect("register.jsp");
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify =helper.Helper.verifyRecaptcha(gRecaptchaResponse);
		
		if (!verify) {
			request.getSession().setAttribute("loginError", "Recaptcha Verification Failed");
			response.sendRedirect("login.jsp");
			return;
		}
		DataBaseHelper dbHelper=new DataBaseHelper();
		if(helper.Helper.isNotEmty(username) && helper.Helper.isNotEmty(password)) {

			if(username.equals(adminUserName) && password.equals(adminPassword) ) {
				request.getSession().setAttribute("firstname","admin");
				request.getSession().setAttribute("lastname","admin");
				request.getSession().setAttribute("username","admin@isp.net");
				request.getSession().setAttribute("role","admin");

				response.sendRedirect("dashboard.jsp");
				return;
			}

			else {
				UserModel userFromDataBase = dbHelper.getUserFromDataBase(username, password);
				if (userFromDataBase==null) {
					request.getSession().setAttribute("loginError", "Username or Password is wrong!");
					response.sendRedirect("login.jsp");
					return;
				}else {
					request.getSession().setAttribute("firstname",userFromDataBase.getFirstName());
					request.getSession().setAttribute("lastname",userFromDataBase.getLastName());
					request.getSession().setAttribute("username",userFromDataBase.getEmail());
					request.getSession().setAttribute("role",userFromDataBase.getRole());

					response.sendRedirect("dashboard.jsp");
					return;
				}
			}
		}
		else {
			request.getSession().setAttribute("loginError", "Username or Password required!");
			response.sendRedirect("login.jsp");
			return;
		}
		
	}

}
