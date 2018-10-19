<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>A web page</title>
    <style type="text/css">
        .half-width {
            display: inline-block;
            width: 20%;
            margin: 15px;
        }

        .full-width {
            display: inline-block;
            width: 50%;
            margin: 15px;
        }
    </style>
</head>
<body>
<div style="text-align: center">
    <h2>REGISTRATION PAGE</h2>
    <%if (session.getAttribute("registrationError") != null) {%>
    <span style="color: red;"><%=session.getAttribute("registrationError")%></span>
    <%
            session.removeAttribute("registrationError");
        }%>

    <filedset>
        <form action='Register' method='POST'>
            <div>
                <lable for='firstName'>Firstname*:</lable>
                <input class="half-width" type='text' name='firstName' required>
                <lable for='lastName'>Lastname*:</lable>
                <input class="half-width" type='text' name='lastName' required>
            </div>
            <div>
                <lable for='address'>Address*:</lable>
                <input class="full-width" type='text' name='address' required>
            </div>
            <div>
                <lable for='email'>Email*:</lable>
                <input class="half-width" type='text' name='email' required>
            </div>
            <div>
                <lable for='password'>Password*:</lable>
                <input class="half-width" type='password' name='password' required>
            </div>
            <div>
                <lable for='confirmPassword'>Confirm Password*:</lable>
                <input class="half-width" type='password' name='confirmPassword' required>
            </div>
            <div>
                <input type='checkbox' name='agreement' value='agreement'>
                I agree to the <a href='./termsOfService.html'>terms of service</a>
            </div>
            <input type='submit' value='Register'>
            <input type='reset' value='Cancel'>
        </form>
    </filedset>

</div>

</body>
</html>