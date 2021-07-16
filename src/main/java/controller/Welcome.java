package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

@WebServlet(value = "/welcome")
public class Welcome extends HttpServlet {

    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> listProducts = productService.getAll();
        List<Category> listCategories = categoryService.getAll();

        if (listProducts != null && listCategories != null) {
            req.setAttribute("listProducts", listProducts);
            req.setAttribute("listCategories", listCategories);
            req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/jsp/index.jsp");
        }
    }
}