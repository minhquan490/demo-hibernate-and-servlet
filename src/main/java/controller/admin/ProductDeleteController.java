package controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/product/delete")
public class ProductDeleteController extends HttpServlet {

    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/view/admin/jsp/list-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        try {
            Product product = productService.getCode(code);
            Files.deleteIfExists(Paths.get("/home/quan/DataForProject/demo-hibernate-and-servlet/Product/" + product.getPicture()));
            productService.delete(code);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        } catch (SQLException e) {
            Log.getLog("ProductDeleteController", e.getMessage(), e);
        }
    }
}