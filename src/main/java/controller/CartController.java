package controller;

import java.io.IOException;
import java.sql.SQLException;
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
import utils.Log;

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
        } else {
            Cart cart = cartService.get(user);
            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setId(user.getId());
                newCart.setUser(user);
                try {
                    cartService.add(newCart);
                    doPost(req, resp);
                    return;
                } catch (SQLException e) {
                    Log.getLog("CartController", e.getMessage(), e);
                }
            } else {
                session.setAttribute("cart", cart);
                doPost(req, resp);
                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");

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
        User user = (User) session.getAttribute("user");
        Cart cart = cartService.get(user);
        Product product = productService.get(Long.valueOf(req.getParameter("idProduct")));

        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int total = product.getPrice() * quantity;

        CartItem cartItem = new CartItem(cart, product, quantity, total);
        cartItemService.save(cartItem);

        Set<CartItem> cartItems = cart.getCartItems();
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);
        cart.setId(user.getId());
        cart.setUser(user);

        cartService.edit(cart);

        resp.sendRedirect(req.getContextPath() + "/cart/list");
    }

    private void editCartItem(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        Cart cart = cartService.get(user);
        Product product = productService.getCode(req.getParameter("code"));

        long idCartItem = Long.parseLong(req.getParameter("idCartItem"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == idCartItem) {
                cartItem.setId(cartItem.getId());
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setTotal(product.getPrice() * quantity);
                cartItemService.edit(cartItem);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/cart/list");
    }

    private void deleteCartItem(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        Cart cart = cartService.get(user);

        long idCartItem = Long.parseLong(req.getParameter("idCartItem"));

        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == idCartItem) {
                cartItemService.delete(cartItem.getId());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/cart/list");
    }
}