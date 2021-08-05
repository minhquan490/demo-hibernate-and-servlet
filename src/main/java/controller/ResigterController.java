package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;
import utils.Random;
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
        long id = Long.parseLong(Random.getID("user"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("role_id"));

        User user = new User();

        user.setId(id);

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

        if (userService.checkUsernameExist(username)) {
            message = "Username is existed !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            user.setUsername(username);
        }

        if (userService.checkEmailExist(email)) {
            message = "Email is existed !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        } else {
            user.setEmail(email);
        }

        user.setPassword(password);

        try {
            Boolean success = userService.register(user);
            if (success) {
                SendEmail sendEmail = new SendEmail();
                sendEmail.sendEmail(email, "Demo Hibernate and Servlet", "Please login to use service");
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                message = "Error !";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/client/jsp/resigter.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            Log.getLog("ResigterController", e.getMessage(), e);
        }
    }
}