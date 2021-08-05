<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Admin Page</title>
            </head>

            <body>
                <a href="${pageContext.request.contextPath}/admin/user/list">List User</a><br><br><br>
                <a href="${pageContext.request.contextPath}/admin/product/list">Product List</a><br><br><br>
                <a href="${pageContext.request.contextPath}/welcome">Client Page</a><br><br><br>
                <a href="${pageContext.request.contextPath}/logout">Log out</a>
            </body>

            </html>