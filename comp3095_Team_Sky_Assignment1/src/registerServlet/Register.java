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
package registerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Register() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		helper.Helper valid = new helper.Helper();
		
		String docType = "<!DOCTYPE html>";
		String header = "<html><head><title>Insert title here</title></head><body><h1>REGISTERATION PAGE</h1>";
		
		String bodeyTags ="<filedset>"
							+ "<form action='Login' method='POST'>"
								+ "<lable for='firstName'>Firstname*:</lable>"
									+ "<input type='text' name='firstName' required><br>"
								+ "<lable for='lastName'>Lastname*:</lable>"
									+ "<input type='text' name='lastName' required><br>"
								+ "<lable for='address'>Address*:</lable>"
									+ "<input type='text' name='address' required><br>"
								+ "<lable for='email'>Email*:</lable>"
									+ "<input type='text' name='email' required><br>"
								+ "<lable for='password'>Password*:</lable>"
									+ "<input type='text' name='password' required><br>"
								+ "<lable for='confirmPassword'>Confirm Password*:</lable>"
									+ "<input type='text' name='confirmPassword' required><br>"								
									+ "<input type='checkbox' name='agreement' value='agreement'>"
									+ "I agree to the <a href='./termsOfService.html'>terms of service</a><br>"
									+ "<input type='submit' value='Register'><br>"
							+ "</form>"
						+ "</filedset>"
							+ "<form action='Login' method='POST'>"
							+ "<input type='submit' value='Cancel'>"
							+ "</form>";
		
		String footer = "</body></html>";
		
		String htmlTags = docType + header + bodeyTags + footer;
		
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
//		String address = request.getParameter("address");
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
		
		pw.println(htmlTags);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
