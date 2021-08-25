package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

@WebServlet(value = "/search")
public class SearchController extends HttpServlet {

    CategoryService categoryService = new CategoryServiceImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = req.getParameter("select");

        switch (select) {
            case "1":
                doPost(req, resp);
                break;
            case "0":
                List<Product> listProducts = categoryService.getListProductsFromCategory(req.getParameter("nameCategory"));
                req.setAttribute("listProductSearchByCategory", listProducts);
                req.getRequestDispatcher("/jsp/view/client/jsp/search.jsp").forward(req, resp);
                break;
            default:
                resp.sendError(404);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = productService.searchByName(req.getParameter("productName"));

        req.setAttribute("listProductSearchByName", products);
        req.getRequestDispatcher("/jsp/view/client/jsp/search.jsp").forward(req, resp);
    }
}