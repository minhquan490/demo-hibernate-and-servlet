package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
import service.impl.ProductServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/product/delete")
public class ProductDeleteController extends HttpServlet {
    
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idProduct");
        try {
            productService.delete(Long.parseLong(id));
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        } catch (NumberFormatException e) {
            Log.getLog(ProductDeleteController.class, e.getMessage(), e);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        } catch (SQLException e) {
            Log.getLog(ProductDeleteController.class, e.getMessage(), e);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        }
    }
}