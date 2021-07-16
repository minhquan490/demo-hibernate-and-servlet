package controller;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;
import utils.SendEmail;

@WebServlet(urlPatterns = "/register")
public class ResigterController extends HttpServlet {

    UserService userService = new UserServiceImpl();
    String message = "";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("role_id"));

        try {
            if (roleId == 1) {
                roleId = 2;
            } else {
                roleId = 2;
            }
        } catch (Exception e) {
            roleId = 2;
        }

        if (userService.checkUsernameExist(username)) {
            message = "Username is existed !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        }

        if (userService.checkEmailExist(email)) {
            message = "Email is existed !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/register.jsp").forward(req, resp);
            return;
        }

        if (!userService.checkUsernameExist(username) && !userService.checkEmailExist(email)) {
            try {
                Boolean success = userService.register(username, email, password, roleId);
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
                Log.getLog(ResigterController.class, e.getMessage(), e);
            }
        }
    }
}