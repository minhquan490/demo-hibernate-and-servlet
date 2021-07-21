package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StatusService;
import service.impl.StatusServiceImpl;
import utils.Log;

public class OrderDeleteController extends HttpServlet {
    
    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("idStatus"));
        try {
            statusService.delete(id);
        } catch (SQLException e) {
            Log.getLog("OrderDeleteController", e.getMessage(), e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/order/list");
    }
}