package controller.admin;

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
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

@WebServlet(value = "/admin/product/list")
public class ProductListController extends HttpServlet {

    CategoryService categoryService = new CategoryServiceImpl();
    ProductService productService = new ProductServiceImpl();
    private String message;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<Category> listCategories = categoryService.getAll();
        List<Product> listProducts = productService.getAll();
        if (listProducts == null) {
            message = "No product in database, add product";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/list-product.jsp").forward(req, resp);
        } else {
            session.setAttribute("listCategories", listCategories);
            session.setAttribute("listProducts", listProducts);
            resp.sendRedirect(req.getContextPath() + "/jsp/view/admin/jsp/list-product.jsp");
        }
    }
}