package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

@WebServlet(value = "/product/searchByCategory")
public class ProductSearchByCategoryController extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("category_name");
        List<Category> listCategories = categoryService.searchByCategory(name);
        req.setAttribute("listProductSearchByCategory", listCategories);
        req.getRequestDispatcher("/jsp/view/client/jsp/product-search-by-category.jsp").forward(req, resp);
    }
}