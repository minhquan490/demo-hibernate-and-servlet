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
                            <a href="${pageContext.request.contextPath}/admin/product/add">Add product</a><br><br>
                            <c:if test="${sessionScope.message eq 'List categories is null, add category first'}">
                                <form action="${pageContext.request.contextPath}/admin/category/add" method="post">
                                    Category name:&nbsp;
                                    <input type="text" name="nameCategory"><br>
                                    <button type="submit">Add Category</button>
                                </form>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <c:out value="Product" /><br>
                            <table>
                                <tr>
                                    <th>stt</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Category Name</th>
                                    <th>price</th>
                                    <th>picture</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach items="${listProducts}" var="product">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.code}</td>
                                        <td>${product.name}</td>
                                        <td>
                                            <c:forEach items="${product.categories}" var="category">
                                                <c:out value="${category.name}" />
                                            </c:forEach>
                                        </td>
                                        <td>${product.price}</td>
                                        <td><img src="${pageContext.request.contextPath}/images/${product.picture}" alt="${product.name}" width="50" height="75"></td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/admin/product/delete" method="post">
                                                <input type="hidden" name="code" value="${product.code}">
                                                <button type="submit">Delete</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/product/edit" method="get">
                                                <input type="hidden" name="idProduct" value="${product.id}">
                                                <button type="submit">Edit</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table><br>
                            <c:out value="Category" />
                            <table>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach items="${sessionScope.listCategories}" var="category">
                                    <tr>
                                        <td>${category.id}</td>
                                        <td>${category.name}</td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/admin/category/delete" method="post">
                                                <input type="hidden" name="idCategory" value="${category.id}">
                                                <button type="submit">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <br>
                <a href="${pageContext.request.contextPath}/admin/product/add" type="button">Add product</a>
                <br><br>
                <div>
                    <form action="${pageContext.request.contextPath}/admin/category/add" method="post">
                        Category name:&nbsp;
                        <input type="text" name="nameCategory"><br>
                        <button type="submit">Add Category</button>
                    </form>
                </div><br>
                <a href="${pageContext.request.contextPath}/admin" type="button">Main Page</a>
            </body>

            </html>