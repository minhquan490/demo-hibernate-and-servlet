package controller;

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

@WebServlet(value = "/product/searchByName")
public class ProductSearchByNameController extends HttpServlet {
    
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("product_name");
        List<Product> listProductSearchByName = productService.searchByName(name);
        req.setAttribute("listProductSearchByName", listProductSearchByName);
        req.getRequestDispatcher("/jsp/view/client/jsp/product-search-by-name.jsp").forward(req, resp);
    }
}