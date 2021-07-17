package controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/product/edit")
public class ProductEditController extends HttpServlet {

    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = productService.get(Long.parseLong(req.getParameter("idProduct")));
        List<Category> listCategories = categoryService.getAll();
        req.setAttribute("product", product);
        req.setAttribute("listCategories", listCategories);
        req.getRequestDispatcher("/jsp/view/admin/jsp/edit-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        String name = req.getParameter("nameProduct");
        String price = req.getParameter("price");
        Category category = categoryService.get(req.getParameter("category"));
        String message = "";

        if (name.isBlank()) {
            message = "Product's name is empty";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
            return;
        } else if (productService.checkProductExist(name)) {
            message = "Product is duplicate";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
            return;
        } else {
            product.setName(name);
        }

        if (price.isBlank()) {
            message = "Please input price of product";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
        } else {
            product.setPrice(Integer.parseInt(price));
        }

        if (!ServletFileUpload.isMultipartContent(req)) {
            message = "Nothing to upload";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
            return;
        }

        FileItemFactory itemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemFactory);

        try {
            List<FileItem> listItems = upload.parseRequest(req);
            for (FileItem item : listItems) {
                String contentType = item.getContentType();
                if (!contentType.equals("image/png")) {
                    message = "Only png format is supported";
                    req.setAttribute("message", message);
                    req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
                    return;
                }
                File dir = new File("/home/quan/DataForProject/demo-hibernate-and-servlet/Product" + "/" + product.getName());
                File file = File.createTempFile("img", ".png", dir);
                item.write(file);
                message = "File saved successfully";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
                product.setPicture(file.getPath());
            }

        } catch (FileUploadException e) {
            message = "Upload fail";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
            Log.getLog("ProductEditController", e.getMessage(), e);
        } catch (Exception ex) {
            message = "Can't save";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
            Log.getLog("ProductEditController", ex.getMessage(), ex);
        }

        if (category == null) {
            message = "Please input Category of product";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/edit-product.jsp").forward(req, resp);
        } else {
            product.setCategory(category);
        }

        try {
            productService.edit(product);
        } catch (SQLException e) {
            Log.getLog("ProductEditController", e.getMessage(), e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}