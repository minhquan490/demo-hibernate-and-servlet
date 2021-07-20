package controller;

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

@WebServlet(value = "/order/list")
public class ListOrderController extends HttpServlet {

    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("idCart"));
        List<Status> listStatus = statusService.getListStatusOfUser(id);
        req.setAttribute("listStatus", listStatus);
        req.getRequestDispatcher("/jsp/view/client/jsp/order-detail.jps");
    }
}