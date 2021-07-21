package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Status;
import service.StatusService;
import service.impl.StatusServiceImpl;

@WebServlet(value = "/admin/order/edit")
public class OrderEditController extends HttpServlet {

    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("idStatus"));
        Status status = statusService.find(id);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/jsp/view/admin/jsp/order-controller").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Status status = new Status();
        long id = Long.parseLong(req.getParameter("idStatus"));
        status = statusService.find(id);
        String approvalStatus = req.getParameter("approvalStatus");

        if (approvalStatus.isBlank()) {
            String message = "Enter approval status";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/order-controller.jsp").forward(req, resp);
        } else {
            status.setApprovalStatus(approvalStatus);
        }
    }
}