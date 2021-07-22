<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>My Account</title>
            </head>

            <body>
                <div>
                    <form action="${pageContext.request.contexPath}/myaccount" method="post">
                        <input type="hidden" name="idUser" value="${user.id}"><br>Full Name:&nbsp;
                        <input type="text" name="fullName" value="${user.fullName}"><br>Email:&nbsp;
                        <input type="text" name="email" value="${user.email}" readonly><br>Gender:&nbsp;
                        <select name="gender">
                            <c:choose>
                                <c:when condition="${empty user.gender}">
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </c:when>
                                <c:otherwise>
                                    <c:when condition="${user.gender == 'male'}">
                                        <option value="${user.gender}"><c:out value="${user.gender}"/></option>
                                        <option value="female">Female</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="male">Male</option>
                                        <option value="${user.gender}"><c:out value="${user.gender}"/></option>
                                    </c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </select><br>Birth Date:&nbsp;
                        <input type="date" name="birthDate" value="${user.birthDate}"><br>Address:&nbsp;
                        <input type="text" name="address" value="${user.address}"><br>Phone:&nbsp;
                        <input type="text" name="phone" value="${user.phone}"><br>Username:&nbsp;
                        <input type="text" name="username" value="${user.username}" readonly><br>Password:&nbsp;
                        <input type="password" name="password" value="${user.password}" readonly>
                    </form>
                </div>
            </body>

            </html>