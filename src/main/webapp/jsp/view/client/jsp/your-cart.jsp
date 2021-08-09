<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Manage your cart</title>
            </head>

            <body>
                <div>
                    My Cart:&nbsp;
                    <c:if test="${not empty message}">
                        <c:out value="${message}" />
                    </c:if>
                    <c:choose>
                        <c:when test="${empty listCartItems}">
                            <div>
                                <c:out value="is empty" />
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <br>
                                <table>
                                    <c:forEach items="${listCartItems}" var="cartItem">
                                        <tr>
                                            <th>Product code</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                            <th>Action</th>
                                        </tr>
                                        <tr>
                                            <td>${cartItem.product.code}</td>
                                            <td>${cartItem.product.name}</td>
                                            <td>${cartItem.product.price}</td>
                                            <td>${cartItem.quantity}</td>
                                            <td>${cartItem.total}</td>
                                            <td>
                                                <form action="${pageContext.request.contextPath}/cart" method="get">
                                                    <input type="hidden" name="code" value="${cartItem.product.code}">
                                                    <input type="hidden" name="idCartItem" value="${cartItem.id}">
                                                    <input type="text" name="quantity">
                                                    <input type="submit" name="act" value="edit">
                                                </form>
                                                <br>
                                                <form action="${pageContext.request.contextPath}/cart" method="get">
                                                    <input type="hidden" name="idCartItem" value="${cartItem.id}">
                                                    <input type="submit" name="act" value="delete">
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <br>
                                <div>
                                    <a type="button" href="${pageContext.request.contextPath}/order/add">Order</a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </body>

            </html>