package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CategoryService;
import service.impl.CategoryServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {

    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("idCategory"));
        try {
            categoryService.delete(id);
        } catch (SQLException e) {
            Log.getLog("CategoryDeleteController", e.getMessage(), e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}