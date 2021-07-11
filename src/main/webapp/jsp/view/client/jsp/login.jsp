<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html lang="en">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Login</title>
        </head>

        <body>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="text" name="username" id="username">Username <br>
                <input type="password" name="password" id="password">Password <br>
                <input type="checkbox" name="remember" value="on">Remember Me <br>
                <button type="submit">Login</button>
            </form>
        </body>

        </html>