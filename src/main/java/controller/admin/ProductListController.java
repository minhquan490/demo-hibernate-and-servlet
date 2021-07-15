package controller.admin;

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

@WebServlet(value = "/admin/product/list")
public class ProductListController extends HttpServlet {

    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listProducts = productService.getAll();
        req.setAttribute("listProducts", listProducts);
        req.getRequestDispatcher("/jsp/view/admin/jsp/list-product.jsp").forward(req, resp);
    }
}