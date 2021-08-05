<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>List User</title>
            </head>

            <body>
                <div>
                    <c:out value="List User:" /><br>
                    <table>
                        <tr>
                            <th>
                                <c:out value="ID" />
                            </th>
                            <th>
                                <c:out value="Full Name" />
                            </th>
                            <th>
                                <c:out value="Email" />
                            </th>
                            <th>
                                <c:out value="Gender" />
                            </th>
                            <th>
                                <c:out value="Birth Date" />
                            </th>
                            <th>
                                <c:out value="Address" />
                            </th>
                            <th>
                                <c:out value="Phone" />
                            </th>
                            <th>
                                <c:out value="Username" />
                            </th>
                            <th>
                                <c:out value="Password" />
                            </th>
                            <th>
                                <c:out value="Role ID" />
                            </th>
                        </tr>
                        <c:forEach items="${listUsers}" var="user">
                            <tr>
                                <td>
                                    <c:out value="${user.id}" />
                                </td>
                                <td>
                                    <c:out value="${user.fullName}" />
                                </td>
                                <td>
                                    <c:out value="${user.email}" />
                                </td>
                                <td>
                                    <c:out value="${user.gender}" />
                                </td>
                                <td>
                                    <c:out value="${user.birthDate}" />
                                </td>
                                <td>
                                    <c:out value="${user.address}" />
                                </td>
                                <td>
                                    <c:out value="${user.phone}" />
                                </td>
                                <td>
                                    <c:out value="${user.username}" />
                                </td>
                                <td>
                                    <c:out value="${user.password}" />
                                </td>
                                <td>
                                    <c:out value="${user.roleId}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </table><br><br>
                    <a href="${pageContext.request.contextPath}/admin/user/add">Add User</a><br><br>
                </div>
                <div>
                    <form action="${pageContext.request.contextPath}/admin/user/delete" method="post">
                        <input type="text" name="username">
                        <button type="submit">Delete</button>
                    </form>
                </div>
            </body>

            </html>