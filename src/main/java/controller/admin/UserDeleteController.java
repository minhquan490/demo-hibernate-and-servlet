package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/user/delete")
public class UserDeleteController extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idUser");
        try {
            userService.delete(Long.parseLong(id));
            resp.sendRedirect(req.getContextPath() + "/admin/user/list");
        } catch (NumberFormatException | SQLException e) {
            Log.getLog(UserDeleteController.class, e.getMessage(), e);
            resp.sendRedirect(req.getContextPath() + "/admin/user/list");
        }
    }
}