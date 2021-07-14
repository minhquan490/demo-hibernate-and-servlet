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

@WebServlet(value = "/product/detail")
public class ProductDetailController extends HttpServlet {

    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idProduct = Long.parseLong(req.getParameter("id_product"));
        Product product = productService.get(idProduct);
        List<Category> listCategories = categoryService.getAll();
        req.setAttribute("product", product);
        req.setAttribute("listCategory", listCategories);
        req.getRequestDispatcher("/jsp/view/client/jsp/product-detail.jsp").forward(req, resp);
    }
}