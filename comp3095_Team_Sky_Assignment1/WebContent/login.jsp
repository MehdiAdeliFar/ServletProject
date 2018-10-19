<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A web page</title>
<script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
	<div style="text-align: center;">
		<div
			style="text-align: center; display: inline-block; padding: 40px; border: 1px solid;">
			<h2>Login</h2>
			<br>
			<p style="color: red"></p>
			<!-- Show login message-->
			<%
				if (session.getAttribute("loginError") != null) {
			%>
			<span style="color: red;"><%=session.getAttribute("loginError")%></span>
			<%
				}
			%>
			<form action="Login" method="post">
				<div>
					<label for="username">Username: </label> <input type="text"
						name="username">
				</div>
				<div>
					<label for="password">Password:</label> <input type="password"
						name="password">
				</div>
				<div class= "g-recaptcha" data-sitekey="6LeAhHMUAAAAANVM4Te-E_-Ol8ATN7NTXb_CqN0K"></div>

				<br>
				<div>
					<input type="submit" name="submitLogin" value="Login"> <input
						type="submit" name="gotoRegister" value="Register">

				</div>
			</form>
			<div>
			<p/>
				<a href="#">Forgot your password?</a>
			</div>
		</div>
	</div>

</body>
</html>