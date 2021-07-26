<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Add User</title>
        </head>

        <body>
            <form action="${pageContext.request.contextPath}/admin/user/add" method="post">
                Full Name:&nbsp;
                <input type="text" name="fullName"><br> Email:&nbsp;
                <input type="text" name="email"><br>Gender:&nbsp;
                <select name="gender">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select><br>Birth Date:&nbsp;
                <input type="date" name="birthDate"><br>Address:&nbsp;
                <input type="text" name="address"><br>Phone:&nbsp;
                <input type="text" name="phone"><br>Username:&nbsp;
                <input type="text" name="username"><br>Password:&nbsp;
                <input type="password" name="password"><br>Role ID:&nbsp;
                <input type="text" name="roleId"><br>
                <button type="submit">Add</button>
            </form>
        </body>

        </html>