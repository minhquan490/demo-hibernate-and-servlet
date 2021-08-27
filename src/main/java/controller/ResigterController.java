package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;
import utils.SendEmail;

@WebServlet(urlPatterns = "/register")
public class ResigterController extends HttpServlet {

    UserService userService = new UserServiceImpl();
    String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re-password");
        String gender = req.getParameter("gender");
        String fullName = req.getParameter("fullName");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("role_id"));
        Date birthDate;
        if (req.getParameter("birthDate") == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            birthDate = Date.valueOf(dtf.format(now));
        } else {
            birthDate = Date.valueOf(req.getParameter("birthDate"));
        }
        User user = new User();

        user.setBirthDate(birthDate);

        try {
            if (roleId == 1) {
                roleId = 2;
            } else {
                roleId = 2;
            }
        } catch (Exception e) {
            roleId = 2;
            Log.getLog("ResigterController", e.getMessage(), e);
        }

        user.setRoleId(roleId);

        if (username.isBlank()) {
            message = "Tên đăng nhập trống";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            if (!userService.checkUsernameExist(username)) {
                user.setUsername(username);
            } else {
                message = "Tên đăng nhập đã tồn tại";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
                return;
            }
        }

        if (!password.equals(rePassword)) {
            message = "Mật khẩu và nhập lại mật khẩu không đồng nhất";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            user.setPassword(rePassword);
        }

        if (fullName.isBlank()) {
            message = "Nhập tên của bạn";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            user.setFullName(fullName);
        }

        if (address.isBlank()) {
            message = "Nhập địa chỉ";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            user.setAddress(address);
        }

        if (phone.isBlank()) {
            message = "Nhập số điện thoại của bạn";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            user.setPhone(phone);
        }

        if (email.isBlank()) {
            message = "Vui lòng nhập email";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            if (!userService.checkEmailExist(email)) {
                user.setEmail(email);
            } else {
                message = "Email đã tồn tại";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
                return;
            }
        }

        user.setGender(gender);
        user.setAvatar("update later");

        try {
            Boolean success = userService.register(user);
            if (success) {
                SendEmail sendEmail = new SendEmail();
                sendEmail.sendEmail(email, "Demo Hibernate and Servlet", "Please login to use service");
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                message = "Error !";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            Log.getLog("ResigterController", e.getMessage(), e);
        }
    }
}