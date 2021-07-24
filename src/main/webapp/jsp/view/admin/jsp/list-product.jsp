<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>List Product</title>
            </head>

            <body>
                <div>
                    <c:choose>
                        <c:when test="${not empty message}">
                            <c:out value="${message}" /><br><br>
                        </c:when>
                        <c:otherwise>
                            <c:out value="Product" /><br>
                            <table>
                                <c:forEach items="listProducts" var="product">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                    </tr>
                                </c:forEach><br> o
                                <c:out value="Category" />
                                <c:forEach items="listCategories" var="category">
                                    <tr>
                                        <td>${category.id}</td>
                                        <td>${category.name}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <br>
                <a href="${pageContext.request.contextPath}/admin/product/add">Add product</a>
                <br><br>
                <div>
                    <form action="${pageContext.request.contextPath}/admin/category/add" method="post">
                        Category name:&nbsp;
                        <input type="text" name="nameCategory"><br>
                        <button type="submit">Add Category</button>
                    </form>
                    <br>
                </div>
            </body>

            </html>