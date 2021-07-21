package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Status;
import service.CartService;
import service.StatusService;
import service.impl.CartServiceImpl;
import service.impl.StatusServiceImpl;
import utils.Log;

@WebServlet(value = "/order/add")
public class AddOrderController extends HttpServlet {

    CartService cartService = new CartServiceImpl();
    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("idCart"));
        Cart cart = cartService.get(id);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date buyDate = Date.valueOf(dtf.format(now));

        String approvalStatus = "Processing";

        try {
            statusService.save(new Status(cart, approvalStatus, buyDate));
        } catch (SQLException e) {
            Log.getLog("AddOrderController", e.getMessage(), e);
        }

        resp.sendRedirect(req.getContextPath() + "/order/list");
    }
}