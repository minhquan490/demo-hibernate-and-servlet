package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet(value = "/sale")
public class ListProductHasSaleController extends HttpServlet {

    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = req.getParameter("re");

        if (!result.equals("all")) {
            result = "a";
            List<Product> listProductsHaveSale = productService.getSaleProduct(result);
            req.setAttribute("listProductsHaveSale", listProductsHaveSale);
            req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
        } else {
            List<Product> listProductsHaveSale = productService.getSaleProduct(result);
            req.setAttribute("listProductsHaveSale", listProductsHaveSale);
            req.getRequestDispatcher("/jsp/view/client/jsp/notification.jsp").forward(req, resp);
        }
    }
}