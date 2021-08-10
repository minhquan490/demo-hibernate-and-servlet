package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

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
        Status status = statusService.find(Long.parseLong(req.getParameter("idOrder")));
        req.setAttribute("order", status);
        req.getRequestDispatcher("/jsp/view/admin/jsp/edit-order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Status status = new Status();
        String id = req.getParameter("idOrder");
        String approvalStatus = req.getParameter("approvalStatus");

        status.setId(Long.parseLong(id));

        if (approvalStatus.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/admin/order/list");
            return;
        }

        status.setApprovalStatus(approvalStatus);

        try {
            statusService.edit(status);
            resp.sendRedirect(req.getContextPath() + "/admin/order/list");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}