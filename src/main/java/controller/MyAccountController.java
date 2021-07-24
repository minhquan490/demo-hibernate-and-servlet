package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = req.getSession(false);
        if (session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String fullName = req.getParameter("fullName");
        Date birthDate = Date.valueOf(req.getParameter("birthDate"));
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        User user = (User) session.getAttribute("user");
        User newUser = new User();

        newUser.setId(user.getId());

        if (!fullName.isBlank()) {
            newUser.setFullName(fullName);
            //req.setAttribute("fullName", user.getFullName());
            //req.getRequestDispatcher("/jsp/view/client/jsp/test.jsp").forward(req, resp);
        } else {
            message = "Enter full name";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        }

        newUser.setEmail(user.getEmail());
        newUser.setGender(gender);
        newUser.setBirthDate(birthDate);

        if (address.isBlank()) {
            message = "Enter address";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        } else {
            newUser.setAddress(address);
        }

        if (phone.isBlank() && phone.matches(PHONE_VALID)) {
            message = "Enter phone number with 10 digit";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        } else {
            newUser.setPhone(phone);
        }

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRoleId(user.getRoleId());

        try {
            userService.edit(newUser);
        } catch (SQLException e) {
            message = "Can't edit";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            Log.getLog("MyAccountController", e.getMessage(), e);
        }
    }
}