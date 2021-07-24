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

    private String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        } else if (user.getAddress() != null && user.getPhone() != null) {
            Cart cart = cartService.get(user.getId());
            List<CartItem> listCartItem = cartItemService.getListCartItemsOfCart(cart);
            req.setAttribute("listCartItem", listCartItem);
            req.getRequestDispatcher("/jsp/view/client/jsp/your-cart.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/welcome");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getI(Long.parseLong(req.getParameter("idUser")));

        if (user.getAddress() == null && user.getPhone() == null) {
            message = "Update your infomation to order";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        }

        String act = req.getParameter("act");

        try {
            switch (act) {
                case "add":
                    add(req, resp);
                    break;
                case "delete":
                    delete(req, resp);
                    break;
                case "edit":
                    edit(req, resp);
                    break;
                default:
                    message = "Error !";
                    req.setAttribute("message", message);
                    req.getRequestDispatcher("/jsp/index.jsp");
                    break;
            }
        } catch (Exception e) {
            Log.getLog("CartController", e.getMessage(), e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            Product product = productService.get(Long.parseLong(req.getParameter("idProduct")));
            Cart cart = cartService.get(Long.parseLong(req.getParameter("idCart")));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int total = quantity * product.getPrice();
            CartItem cartItem = new CartItem(cart, product, quantity, total);
            cartItemService.save(cartItem);
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (Exception e) {
            Log.getLog("CartController", e.getMessage(), e);
            message = "Can't add to cart";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/product-list.jsp").forward(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            cartItemService.delete(Long.parseLong(req.getParameter("idCartItem")));
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (Exception e) {
            Log.getLog("CartController", e.getMessage(), e);
            resp.sendRedirect(req.getContextPath() + "/cart");
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            Product product = productService.get(Long.parseLong(req.getParameter("idProduct")));
            Cart cart = cartService.get(Long.parseLong(req.getParameter("idCart")));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int total = quantity * product.getPrice();
            CartItem cartItem = new CartItem(cart, product, quantity, total);
            cartItemService.edit(cartItem);
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (Exception e) {
            Log.getLog("CartController", e.getMessage(), e);
            message = "Can't edit cart";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/product-list.jsp").forward(req, resp);
        }
    }
}