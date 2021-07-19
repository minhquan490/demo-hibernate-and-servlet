package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;

@WebServlet(value = "/myaccount")
public class MyAccountController extends HttpServlet {

    private static final String PHONE_VALID = "^[0-9]{10}$";

    UserService userService = new UserServiceImpl();
    private String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            String id = req.getParameter("idUser");
            User user = userService.get(Long.parseLong(id));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("idUser"));
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        Date birthDate = Date.valueOf(req.getParameter("birthDate"));
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();

        user.setId(id);

        if (fullName.isBlank()) {
            message = "Enter full name";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        } else {
            user.setFullName(fullName);
        }

        user.setEmail(email);
        user.setGender(gender);
        user.setBirthDate(birthDate);

        if (address.isBlank()) {
            message = "Enter address";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        } else {
            user.setAddress(address);
        }

        if (phone.isBlank() && phone.matches(PHONE_VALID)) {
            message = "Enter phone number with 10 digit";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        } else {
            user.setPhone(phone);
        }

        user.setUsername(username);
        user.setPassword(password);

        try {
            userService.edit(user);
        } catch (SQLException e) {
            message = "Can't edit";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            Log.getLog("MyAccountController", e.getMessage(), e);
        }
    }
}