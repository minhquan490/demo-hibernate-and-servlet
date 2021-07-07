<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resigter</title>
    </head>

    <body>
        <form action="register" method="post">
            <input type="hidden" name="role_id" value="1"><br>Username:
            <input type="text" name="username" id="username"><br>Password:
            <input type="password" name="password" id="password"><br>Email:
            <input type="text" name="email" id="email"><br>
            <button type="submit">Resigter</button>
        </form>
    </body>

    </html>