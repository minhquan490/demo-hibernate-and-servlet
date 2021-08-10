<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>List Order</title>
            </head>

            <body>
                <div>
                    <c:choose>
                        <c:when test="${empty listStatus}">
                            <c:out value="Oder empty" />
                        </c:when>
                        <c:otherwise>
                            <table>
                                <c:if test="${not empty listStatus}">
                                    <tr>
                                        <th>ID</th>
                                        <th>Cart ID</th>
                                        <th>Approval Status</th>
                                        <th>Buy Date</th>
                                        <th>Bill</th>
                                    </tr>
                                </c:if>
                                <tr>
                                    <c:forEach items="${listStatus}" var="order">
                                        <td>${order.id}</td>
                                        <td>${order.idCart}</td>
                                        <td>${order.approvalStatus}</td>
                                        <td>${order.buyDate}</td>
                                        <td>${order.billLink}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/text/${order.billLink}" type="button">More Detail</a>
                                            <form action="${pageContext.request.contextPath}/admin/order/edit" method="get">
                                                <input type="hidden" name="idOrder" value="${order.id}">
                                                <button type="submit">Edit</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/order/delete" method="get">
                                                <input type="hidden" name="idOrder" value="${order.id}">
                                                <button type="submit">Delete</button>
                                            </form>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </body>

            </html>