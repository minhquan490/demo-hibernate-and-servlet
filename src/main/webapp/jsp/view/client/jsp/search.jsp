<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Search</title>
            </head>

            <body>
                <div>
                    <div>
                        <div>
                            <c:choose>
                                <c:when test="${selector == '0'}">
                                    <table>
                                        <tr>
                                            <th>Code</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Picture</th>
                                            <th>Action</th>
                                        </tr>
                                        <c:forEach items="${requestScope.listProductSearchByName}" var="product">
                                            <tr>
                                                <td>${product.code}</td>
                                                <td>${product.name}</td>
                                                <td>${product.price}</td>
                                                <td><img src="${pageContext.request.contextPath}/images/${product.picture}" alt="${product.name}" width="50" height="75"></td>
                                                <td>
                                                    <div>
                                                        <form action="${pageContext.request.contextPath}/product/detail" method="get">
                                                            <input type="hidden" name="idProduct" value="${product.id}">
                                                            <button type="submit">View</button>
                                                        </form>
                                                    </div>
                                                    <div>
                                                        <form action="${pageContext.request.contextPath}/cart" method="get">
                                                            <input type="hidden" name="idProduct" value="${product.id}">
                                                            <input type="text" name="quantity">
                                                            <input type="submit" name="act" value="add">
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <table>
                                        <tr>
                                            <th>Code</th>
                                            <th>Name</th>
                                            <th>Categories</th>
                                            <th>Price</th>
                                            <th>Picture</th>
                                            <th>Action</th>
                                        </tr>
                                        <tr>
                                            <c:forEach items="${requestScope.listProductSearchByCategory}" var="product">
                                                <td>${product.code}</td>
                                                <td>${product.name}</td>
                                                <td>${product.price}</td>
                                                <td><img src="${pageContext.request.contextPath}/images/${product.picture}" alt="${product.name}" width="50" height="75"></td>
                                                <td>
                                                    <div>
                                                        <form action="${pageContext.request.contextPath}/product/detail" method="get">
                                                            <input type="hidden" name="idProduct" value="${product.id}">
                                                            <button type="submit">View</button>
                                                        </form>
                                                    </div>
                                                    <div>
                                                        <form action="${pageContext.request.contextPath}/cart" method="get">
                                                            <input type="hidden" name="idProduct" value="${product.id}">
                                                            <input type="text" name="quantity">
                                                            <input type="submit" name="act" value="add">
                                                        </form>
                                                    </div>
                                                </td>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </body>

            </html>