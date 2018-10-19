<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>


<style type="text/css">
    ul li {
        display: inline;
        padding: 5px;
    }

    ul {
        list-style: none;
        display: inline;
    }

    .welcome {
        display: inline-block;
        float: right;
    }

    .header {
        border-bottom: 1px solid;
        padding-bottom: 5px;

    }

    .centered {
        text-align: center;
    }

    .bordered {
        border: 1px solid black;
    }

    .left-div {
        display: inline-block;

        width: 50%;

        float: left;

        height: 435px;
    }

    .right-div {
        display: inline-block;

        float: right;

        width: 40%;

        height: 216px
    }

</style>


<div class="header">
    <ul>
        <li><a href="dashboard?page=tab1">Tab1</a></li>
        <li><a href="dashboard?page=tab2"> Tab2</a></li>
        <li><a href="dashboard?page=tab3"> Tab3</a></li>
        <li><a href="dashboard?page=tab4">Tab4</a></li>
    </ul>
    <div class="welcome">
        Welcome <%=session.getAttribute("firstname")+" "+session.getAttribute("lastname")%><a href="Login?logout=true">LOGOUT</a>
    </div>

</div>
<div class="centered">
    <% if (session.getAttribute("username") == null) {
        session.setAttribute("loginErroe","A User requests a page that requires a user to be successfully logged in first");
        response.sendRedirect("login.jsp");} %>

    <%if (session.getAttribute("page") == null) {%>
    <jsp:include page="WEB-INF/home.jsp"></jsp:include>
    <%} else {%>
    <jsp:include page="WEB-INF/underConstruction.jsp"></jsp:include>
    <%
            session.removeAttribute("page");
        }
    %>
</div>
