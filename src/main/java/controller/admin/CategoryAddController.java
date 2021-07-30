package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/category/add")
public class CategoryAddController extends HttpServlet {

    CategoryService categoryService = new CategoryServiceImpl();
    ProductService productService = new ProductServiceImpl();
    private String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> listCategories = categoryService.getAll();
        if (listCategories == null) {
            message = "No category in database";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/list-product.jsp").forward(req, resp);
        } else {
            req.setAttribute("listCategories", listCategories);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nameCategory");
        Category category = new Category();

        if (name.isBlank()) {
            message = "Enter name of category";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/list-product.jsp").forward(req, resp);
        } else {
            category.setName(name);
        }

        try {
            categoryService.save(category);
        } catch (SQLException e) {
            Log.getLog("CategoryAddController", e.getMessage(), e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}