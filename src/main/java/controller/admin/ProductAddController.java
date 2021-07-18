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
import javax.servlet.http.HttpSession;

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

@WebServlet(value = "/admin/product/add")
public class ProductAddController extends HttpServlet {

    CategoryService categoryService = new CategoryServiceImpl();
    ProductService productService = new ProductServiceImpl();
    String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<Category> listCategories = categoryService.getAll();
        if (listCategories == null) {
            message = "List categories is null, add category first";
            session.setAttribute("message", message);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        } else {
            req.setAttribute("listCategories", listCategories);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        String productName = req.getParameter("productName");
        int price = Integer.parseInt(req.getParameter("price"));
        Category category = categoryService.get(req.getParameter("categoryName"));

        if (productName.isBlank()) {
            message = "Name of product is not empty !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            return;
        } else if (productService.checkProductExist(productName)) {
            message = "Product is duplicate";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            return;
        } else {
            product.setName(productName);
        }

        product.setPrice(price);

        if (category == null) {
            message = "Category is not empty !";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            return;
        } else {
            product.setCategory(category);
        }

        if (!ServletFileUpload.isMultipartContent(req)) {
            message = "Nothing to upload";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/view/admin/view/list-product.jsp").forward(req, resp);
            return;
        }

        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);

        try {
            List<FileItem> listItems = upload.parseRequest(req);
            for (FileItem item : listItems) {
                String contentType = item.getContentType();
                if (!contentType.equals("image/png")) {
                    message = "Only png format is supported";
                    req.setAttribute("message", message);
                    req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
                    return;
                }
                File dir = new File("/home/quan/DataForProject/demo-hibernate-and-servlet/Product" + "/" + product.getName());
                File file = File.createTempFile("img", ".png", dir);
                item.write(file);
                message = "Save file successfully !";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
                product.setPicture(file.getPath());
            }
        } catch (FileUploadException e) {
            message = "Upload failure";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            Log.getLog("ProductAddController", e.getMessage(), e);
        } catch (Exception e) {
            message = "Can't save";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            Log.getLog("ProductAddController", e.getMessage(), e);
        }

        try {
            productService.save(product);
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        } catch (SQLException e) {
            message = "Error, check data before save.";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            Log.getLog("ProductAddController", e.getMessage(), e);
        }
    }
}