<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Add Product</title>
            </head>

            <body>
                Message:&nbsp;
                <c:out value="${message}" /><br>
                <form action="${pageContext.request.contextPath}/admin/product/add" method="post" enctype="multipart/form-data">
                    Name:&nbsp;
                    <input type="text" name="productName"><br> Price:&nbsp;
                    <input type="text" name="price"><br>Category:&nbsp;
                    <select name="categoryName">
                        <c:forEach items="${sessionScope.listCategories}" var="category">
                            <option value="${category.name}"><c:out value="${category.name}"/></option>
                        </c:forEach>
                    </select>
                    <br>Picture:&nbsp;
                    <input type="file" name="image"><br>
                    <button type="submit">Add Product</button>
                </form><br>
                <a href="${pageContext.request.contextPath}/admin/product/list" type="button">View list product</a>
            </body>

            </html>