package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet(value = "/admin")
public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "";
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRoleId() != 1) {
            session.removeAttribute("user");
            message = "Error !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/login.jsp").forward(req, resp);;
        } else {
            req.setAttribute("username", user.getUsername());
            message = "Welcome" + user.getFullName();
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/index.jsp").forward(req, resp);
        }
    }
}