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
        List<Category> listCategories = categoryService.getAll();
        List<Product> listProducts = productService.getAll();
        List<Product> listProductsHasSale = productService.getSaleProduct("a");

        req.setAttribute("listCategories", listCategories);
        req.setAttribute("listProducts", listProducts);
        req.setAttribute("listProductsHasSale", listProductsHasSale);
        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }
}