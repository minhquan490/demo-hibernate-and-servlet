package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Status;
import service.StatusService;
import service.impl.StatusServiceImpl;

@WebServlet(value = "/admin/order/list")
public class OrderListController extends HttpServlet {

    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Status> listStatus = statusService.getAll();
        req.setAttribute("listStatus", listStatus);
        req.getRequestDispatcher("/jsp/view/admin/jsp/list-order.jsp").forward(req, resp);
    }
}