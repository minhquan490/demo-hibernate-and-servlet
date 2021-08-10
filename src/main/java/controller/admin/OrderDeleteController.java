package controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Status;
import service.StatusService;
import service.impl.StatusServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/order/delete")
public class OrderDeleteController extends HttpServlet {

    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("idOrder"));
        try {
            Status status = statusService.find(id);
            statusService.delete(id);
            Files.deleteIfExists(Paths.get("/home/quan/DataForProject/demo-hibernate-and-servlet/Bill/" + status.getBillLink()));
            resp.sendRedirect(req.getContextPath() + "/admin/order/list");
        } catch (SQLException e) {
            Log.getLog("OrderDeleteController", e.getMessage(), e);
        }
    }
}