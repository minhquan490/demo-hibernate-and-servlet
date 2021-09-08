<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html lang="vi">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
            <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
            <link rel="stylesheet" href="./jsp/view/assets/css/index.css">
            <link rel="stylesheet" href="./jsp/view/assets/css/register.css">
            <link rel="stylesheet" href="./jsp/view/assets/css/base.css">
            <link rel="stylesheet" href="./jsp/view/assets/css/reponsive.css">
            <link rel="stylesheet" href="./jsp/view/assets/fonts/fontawesome/css/all.min.css">

            <title>Resigter</title>
        </head>

        <body>
            <div class="main">
                <jsp:include page="/jsp/header.jsp" />
                <div class="register-container">
                    <div class="gird">
                        <div class="register-wrapper">
                            <div class="register__header">
                                <div class="register__header-login">
                                    <a href="${pageContext.request.contextPath}/login" class="register__header-link btn">Đăng nhập</a>
                                </div>
                                <div class="register__header-register">
                                    <a href="#" class="register__header-link btn" style="border-bottom: 2px solid var(--primary-color); cursor: default;">Đăng ký</a>
                                </div>
                            </div>
                            <div class="register__form">
                                <form action="${pageContext.request.contextPath}/register" method="post">
                                    <input type="hidden" name="role_id" value="2" class="register__form-input">
                                    <input type="text" class="register__form-input" name="username" placeholder="Tên đăng nhập (*)">
                                    <input type="text" class="register__form-input" name="fullName" placeholder="Họ tên (*)">
                                    <div class="register__form-input--wrapper">
                                        <label for="gender" class="register__form-input--label">Giới tính</label>
                                        <select name="gender" id="gender">
                                            <option value="male" class="register__form-input--opt">Nam</option>
                                            <option value="female" class="register__form-input--opt">Nữ</option>
                                        </select>
                                    </div>
                                    <div class="register__form-input--wrapper">
                                        <label for="birthDate" class="register__form-input--label">Ngày sinh</label>
                                        <input type="date" class="register__form-input" name="birthDate" id="birthDate">
                                    </div>
                                    <input type="text" class="register__form-input" name="address" placeholder="Địa chỉ (*)">
                                    <input type="number" class="register__form-input" name="phone" placeholder="Điện thoại (*)">
                                    <input type="password" class="register__form-input" name="password" placeholder="Mật khẩu (*)">
                                    <input type="password" class="register__form-input" name="re-password" placeholder="Nhập lại mật khẩu (*)">
                                    <input type="text" class="register__form-input" name="email" placeholder="Email (*)">
                                    <button class="btn register__form-btn" type="submit">Đăng ký</button>
                                    <!-- TODO modify register servlet -->
                                </form>
                                <div class="register__footer">
                                    <c:choose>
                                        <c:when test="${empty requestScope.message}">
                                            <span class="register__footer-content">Trường hợp có dấu * là bắt buộc</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="register__footer-content" style="color: red;">
                                                <c:out value="${requestScope.message}"/>
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="/jsp/footer.jsp" />
            </div>
        </body>

        </html>