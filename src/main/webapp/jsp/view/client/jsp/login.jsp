<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html lang="vi">

        <head>
            <meta charset="UTF-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
            <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
            <link rel="stylesheet" href="./jsp/view/assets/css/index.css" />
            <link rel="stylesheet" href="./jsp/view/assets/css/login.css" />
            <link rel="stylesheet" href="./jsp/view/assets/css/base.css" />
            <link rel="stylesheet" href="./jsp/view/assets/css/reponsive.css" />
            <link rel="stylesheet" href="./jsp/view/assets/fonts/fontawesome/css/all.min.css" />

            <title>Resigter</title>
        </head>

        <body>
            <div class="main">
                <jsp:include page="/jsp/header.jsp" />
                <div class="gird">
                    <div class="login-wrapper">
                        <div class="login__header">
                            <div class="login__header-login">
                                <a href="#" class="login__header-link btn" style="border-bottom: 2px solid var(--primary-color); cursor: default;">Đăng nhập</a>
                            </div>
                            <div class="login__header-register">
                                <a href="${pageContext.request.contextPath}/register" class="login__header-link btn" id="disable">Đăng ký</a>
                            </div>
                        </div>
                        <div class="login__form">
                            <form action="${pageContext.request.contextPath}/login" method="post">
                                <input type="text" class="login__form-input" name="username" placeholder="Tài khoản" />
                                <input type="password" class="login__form-input" name="password" placeholder="Mật khẩu" />
                                <div class="login__form-checkbox--wrapper">
                                    <label for="remember" class="login__form-checkbox">Ghi nhớ tài khoản</label>
                                    <input type="checkbox" class="login__form-checkbox" id="remember" name="remember">
                                </div>
                                <button type="submit" class="btn login__form-btn">Đăng nhập</button>
                            </form>
                        </div>
                        <div class="login__footer">
                            <a href="${pageContext.request.contextPath}/user/forgot" class="login__footer-content">Quên mật khẩu ?</a>
                        </div>
                    </div>
                </div>
                <jsp:include page="/jsp/footer.jsp" />
            </div>
        </body>

        </html>