package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.Product;
import model.User;
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
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            try {
                List<Product> listProducts = productService.getAll();
                List<Category> listCategories = categoryService.getAll();
                User user = (User) session.getAttribute("user");
                String message = "Has User";
                req.setAttribute("message", message);
                req.setAttribute("listProducts", listProducts);
                req.setAttribute("listCategories", listCategories);
                req.setAttribute("roleId", user.getRoleId());
                req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
            } catch (Exception e) {
                List<Product> listProducts = productService.getAll();
                List<Category> listCategories = categoryService.getAll();
                String message = "Exception on User " + e.getMessage();
                req.setAttribute("message", message);
                req.setAttribute("listProducts", listProducts);
                req.setAttribute("listCategories", listCategories);
                req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
            }
        } else {
            List<Product> listProducts = productService.getAll();
            List<Category> listCategories = categoryService.getAll();
            String message = "No User";
            req.setAttribute("message", message);
            req.setAttribute("listProducts", listProducts);
            req.setAttribute("listCategories", listCategories);
            req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
        }
    }
}