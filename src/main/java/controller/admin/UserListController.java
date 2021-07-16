package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(value = "/admin/user/list")
public class UserListController extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userService.getAll();
        String message = "";

        if (users == null) {
            message = "No user in database";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("listUsers", users);
            req.getRequestDispatcher("/jsp/view/admin/jsp/list-user.jsp").forward(req, resp);
        }
    }
}