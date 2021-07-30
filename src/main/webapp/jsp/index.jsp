<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html lang="en">

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Demo Hibernate and Servlet</title>
            </head>

            <body>
                <c:out value="Product" /><br>
                <table>
                    <c:forEach items="${listProducts}" var="product">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.category.name}</td>
                            <td>${product.price}</td>
                            <td>${product.picture}</td>
                        </tr>
                    </c:forEach>
                </table><br><br>
                <c:out value="Category" /><br>
                <table>
                    <c:forEach items="${listCategories}" var="category">
                        <tr>
                            <td>${category.name}</td>
                        </tr>
                    </c:forEach>
                </table><br>
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/login">Login</a> <br>
                        <a href="${pageContext.request.contextPath}/register">Register</a> <br>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/myaccount">My Account</a><br>
                        <a href="${pageContext.request.contextPath}/logout">Logout</a><br>
                        <c:choose>
                            <c:when test="${roleId == '1'}">
                                <a href="${pageContext.request.contextPath}/admin">Admin Page</a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="" />
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </body>

            </html>