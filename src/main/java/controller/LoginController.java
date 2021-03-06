package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {

    UserService userService = new UserServiceImpl();

    private void remember(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(60 * 60);
        resp.addCookie(cookie);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/wating");
            return;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    req.setAttribute("username", cookie.getValue());
                    req.getRequestDispatcher("/jsp/view/client/jsp/login.jsp").forward(req, resp);
                    return;
                }
            }
        }
        req.getRequestDispatcher("/jsp/view/client/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String message = "";
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember");
        boolean isRemember = false;

        if (username.isBlank() || password.isBlank()) {
            message = "username or password is empty !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/login.jsp").forward(req, resp);
            return;
        }

        try {
            if (rememberMe.equals("on")) {
                isRemember = true;
            }
        } catch (Exception e) {
            Log.getLog("LoginController", e.getMessage(), e);
        }

        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute("user", user);
            if (isRemember) {
                remember(resp, username);
            }
            resp.sendRedirect(req.getContextPath() + "/wating");
        } else {
            message = "Username or password isn't correct";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/login.jsp").forward(req, resp);
        }
    }
}