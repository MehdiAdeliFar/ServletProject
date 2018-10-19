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

import helper.DataBaseHelper;
import helper.Helper;
import model.UserModel;

import java.io.IOException;

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

        response.sendRedirect("register.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String agreement = request.getParameter("agreement");
        String errorText = "";
        if (!Helper.isNotEmty(firstName) || !Helper.isNotEmty(lastName) || !Helper.isNotEmty(address) || !Helper.isNotEmty(email) || !Helper.isNotEmty(password) || !Helper.isNotEmty(confirmPassword))
            errorText += "Fill All data boxes!<br/>";
        if (!Helper.isNotEmty(agreement)) errorText += "You should accept terms of service!<br/>";
        if (!password.equals(confirmPassword)) errorText += "Password and confirm password not match!<br/>";
        Helper helper = new Helper();
        if (!helper.validateEmail(email)) errorText += "Invalid Email!<br/>";
        errorText+=helper.validatePassword(password);
        if (!helper.validateCharacter(firstName) || !helper.validateCharacter(lastName))
            errorText+="first name and last name must alphabets!";
        DataBaseHelper dbHelper=new DataBaseHelper();
        if (dbHelper.checkUserExist(email))
            errorText+="Email exists select another one!";
        if (errorText.length()>0){
            redirectToRegister(request,response,errorText);
            return;
        }
        int i = dbHelper.addUserToDatabase(new UserModel(0, firstName, lastName, email, address, password, "user"));
        if (i>=0) {
            String result = "<div style=\"text-align: center;\">\n" +
                    "    <div\n" +
                    "            style=\"text-align: center; display: inline-block; padding: 40px; \">\n" +
                    "        <h2>Successfully registered user </h2>\n" +
                    "        <br>\n" +
                    "        <div style=\"display: inline-block;height: 100px;padding: 30px; border: 1px solid black\">\n" +
                    "            <img src=\"done.png\" alt=\"done\" width=\"30\">\n" +
                    "            <p style=\"display: inline\">an email has been sent to: {email}. Please check your email to verifyRecaptcha and\n" +
                    "                confirm</p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>";
            String emailText = "Hello dear {firstname}\n\r you registered in web application using this information\n\r" +
                    "first name: {firstname}\n" +
                    "last name:{lastname}\n" +
                    "username:{username}\n" +
                    "and you can login to web app fy clicking on this link: {link}";
            emailText=emailText.replace("{firstname}",firstName).replace("{lastname}",lastName).replace("{username}",email).replace("{link}",Helper.getBaseUrl(request));
            Helper.sendEmail(email,"Account verification email", emailText);
            result=result.replace("{email}",email);
            response.getWriter().write(result);
        }
    }

    private void redirectToRegister(HttpServletRequest request, HttpServletResponse response, String o) throws IOException {
        request.getSession().setAttribute("registrationError", o);
        response.sendRedirect("register.jsp");
    }

}
