package controller.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/user/add")
public class UserAddController extends HttpServlet {

    private static final String PHONE_VALID = "^[0-9]{10}$";

    UserService userService = new UserServiceImpl();
    private String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/jsp/view/admin/jsp/add-user.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        Date birthDate = Date.valueOf(req.getParameter("birthDate"));
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();

        if (fullName.isBlank()) {
            message = "Enter full name";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-user.jsp").forward(req, resp);
            return;
        } else {
            user.setFullName(fullName);
        }

        if (email.isBlank() && userService.checkEmailExist(email)) {
            message = "Email is empty or existed";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-user.jsp").forward(req, resp);
            return;
        } else {
            user.setEmail(email);
        }

        user.setGender(gender);
        user.setBirthDate(birthDate);

        if (address.isBlank()) {
            message = "Enter address";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-user.jsp").forward(req, resp);
            return;
        } else {
            user.setAddress(address);
        }

        if (phone.isBlank() && phone.matches(PHONE_VALID)) {
            message = "Enter phone number with 10 digit";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-user.jsp").forward(req, resp);
            return;
        } else {
            user.setPhone(phone);
        }

        if (username.isBlank() && userService.checkUsernameExist(username)) {
            message = "Username is empty or existed";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-user.jsp").forward(req, resp);
            return;
        } else {
            user.setUsername(username);
        }

        user.setPassword(password);

        try {
            userService.save(user);
        } catch (Exception e) {
            message = "Can't add user";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-user.jsp").forward(req, resp);
            Log.getLog("UserAddController", e.getMessage(), e);
        }
    }
}