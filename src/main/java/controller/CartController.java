package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;
import model.Product;
import model.User;
import service.CartItemService;
import service.CartService;
import service.ProductService;
import service.UserService;
import service.impl.CartItemServiceImpl;
import service.impl.CartServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
import utils.Random;

@WebServlet(value = "/cart")
public class CartController extends HttpServlet {

    UserService userService = new UserServiceImpl();
    ProductService productService = new ProductServiceImpl();
    CartService cartService = new CartServiceImpl();
    CartItemService cartItemService = new CartItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = getParameter(req, "act");

        switch (act) {
            case "add":
                try {
                    addToCart(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    editCartItem(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteCartItem(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        HttpSession session = req.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        Product product = productService.get(Long.valueOf(req.getParameter("idProduct")));

        long id = Long.parseLong(Random.getID("user"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int total = (product.getPrice() - product.getSalePrice()) * quantity;

        CartItem cartItem = new CartItem(id, cart, product, quantity, total);
        cartItemService.save(cartItem);

        Set<CartItem> cartItems;
        try {
            cartItems = cart.getCartItems();
            cartItems.add(cartItem);
        } catch (Exception e) {
            cartItems = new HashSet<CartItem>();
            cartItems.add(cartItem);
        }

        cart.setCartItems(cartItems);
        cartService.edit(cart);

        session.setAttribute("cart", cart);

        resp.sendRedirect(req.getContextPath() + "/cart/list");
    }

    private void editCartItem(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        HttpSession session = req.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        Product product = productService.getCode(req.getParameter("code"));

        long idCartItem = Long.parseLong(req.getParameter("idCartItem"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == idCartItem) {
                cartItem.setId(idCartItem);
                cartItem.setQuantity(quantity);
                cartItem.setTotal(product.getPrice() * quantity);
                cartItemService.edit(cartItem);
            }
        }

        session.setAttribute("cart", cart);

        resp.sendRedirect(req.getContextPath() + "/cart/list");
    }

    private void deleteCartItem(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        HttpSession session = req.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");

        long idCartItem = Long.parseLong(req.getParameter("idCartItem"));

        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == idCartItem) {
                cartItems.remove(cartItem);
                cartItemService.delete(cartItem.getId());
            }
        }

        cart.setCartItems(cartItems);
        cartService.edit(cart);

        session.setAttribute("cart", cart);

        resp.sendRedirect(req.getContextPath() + "/cart/list");
    }

    private String getParameter(HttpServletRequest req, String parameter) throws IOException, ServletException {
        return req.getParameter(parameter);
    }
}