package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;
import model.Status;
import service.CartItemService;
import service.StatusService;
import service.impl.CartItemServiceImpl;
import service.impl.StatusServiceImpl;
import utils.Random;

@WebServlet(value = "/order/add")
public class AddOrderController extends HttpServlet {

    private static final String SAVE_LOCATION = "/home/quan/DataForProject/demo-hibernate-and-servlet/Bill/";

    CartItemService cartItemService = new CartItemServiceImpl();
    StatusService statusService = new StatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        Set<CartItem> cartItems = cart.getCartItems();
        int fullTotal = 0;
        String message = "";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        Date buyDate = Date.valueOf(dtf.format(now));

        Status status = new Status();
        status.setIdCart(String.valueOf(cart.getId()));
        status.setApprovalStatus("Processing");
        status.setBuyDate(buyDate);

        try {
            String billName = Random.getID("bill");
            File dir = new File(SAVE_LOCATION);
            File save = File.createTempFile(billName, ".txt", dir);
            FileWriter writer = new FileWriter(save);
            for (CartItem cartItem : cartItems) {
                writer.write(cartItem.getProduct().getName());
                writer.write(cartItem.getQuantity());
                fullTotal += cartItem.getTotal();

                cartItems.remove(cartItem);
                cartItemService.delete(cartItem.getId());
            }
            writer.write(fullTotal);
            writer.close();
            status.setBillLink(save.getAbsolutePath());
        } catch (IOException e) {
            message = "Error";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/your-cart.jsp").forward(req, resp);
            return;
        }

        try {
            statusService.save(status);
            resp.sendRedirect(req.getContextPath() + "/welcome");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}