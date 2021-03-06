<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Product Detail</title>
            </head>

            <body>
                <div>
                    <c:out value="${product.code}" /><br>
                    <c:out value="${product.name}" /><br>
                    <c:out value="${product.category.name}" /><br>
                    <c:out value="${product.price}" /><br>
                    <img src="${pageContext.request.contextPath}/images/${product.picture}" alt="${product.name}" width="50" height="75">
                </div>
            </body>

            </html>