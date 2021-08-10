package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Status;
import model.User;
import service.CartService;
import service.StatusService;
import service.impl.CartServiceImpl;
import service.impl.StatusServiceImpl;

@WebServlet(value = "/myorder")
public class MyOrder extends HttpServlet {

    CartService cartService = new CartServiceImpl();
    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Cart cart = cartService.get(user);
        List<Status> myOrders = null;
        try {
            myOrders = statusService.getListStatusOfUser(String.valueOf(cart.getId()));
            req.setAttribute("myOrders", myOrders);
            req.getRequestDispatcher("/jsp/view/client/jsp/order-detail.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            req.setAttribute("myOrders", myOrders);
            req.getRequestDispatcher("/jsp/view/client/jsp/order-detail.jsp").forward(req, resp);
        }
    }
}