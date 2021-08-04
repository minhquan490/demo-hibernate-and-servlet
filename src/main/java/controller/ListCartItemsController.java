package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;
import model.User;
import service.CartService;
import service.UserService;
import service.impl.CartServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet(value = "/cart/list")
public class ListCartItemsController extends HttpServlet {

    CartService cartService = new CartServiceImpl();
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        Cart cart = cartService.get(user);
        List<CartItem> listCartItems = new ArrayList<CartItem>();
        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            listCartItems.add(cartItem);
        }
        req.setAttribute("listCartItems", listCartItems);
        req.getRequestDispatcher("/jsp/view/client/jsp/your-cart.jsp").forward(req, resp);;
    }
}