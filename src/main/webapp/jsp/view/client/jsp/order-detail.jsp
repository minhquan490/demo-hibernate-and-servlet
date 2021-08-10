<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>My Order</title>
            </head>

            <body>
                <div>
                    <c:choose>
                        <c:when test="${empty myOrders}">
                            <c:out value="Empty" />
                        </c:when>
                        <c:otherwise>
                            <table>
                                <c:if test="${not empty myOrders}">
                                    <tr>
                                        <th>Approval Status</th>
                                        <th>Buy Date</th>
                                    </tr>
                                </c:if>
                                <tr>
                                    <c:forEach items="${myOrders}" var="order">
                                        <td>${order.approvalStatus}</td>
                                        <td>${order.buyDate}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/text/${order.billLink}" type="button">Detail</a>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </body>

            </html>