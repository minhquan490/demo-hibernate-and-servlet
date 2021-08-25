package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.User;
import service.CartService;
import service.impl.CartServiceImpl;

@WebServlet(value = "/wating")
public class Wating extends HttpServlet{

    CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRoleId() == 1) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                Cart cart = cartService.get(user);
                if (cart == null) {
                    Cart newCart = new Cart();
                    newCart.setId(user.getId());
                    newCart.setUser(user);
                    try {
                        cartService.add(cart);
                        session.setAttribute("cart", cart);
                        session.setAttribute("totalItem", 0);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    session.setAttribute("cart", cart);
                    session.setAttribute("totalItem", cart.getCartItems().size());
                }
                resp.sendRedirect(req.getContextPath() + "/welcome");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}