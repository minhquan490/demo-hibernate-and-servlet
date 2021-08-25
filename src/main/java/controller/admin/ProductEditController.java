package controller.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import utils.Log;

@WebServlet(value = "/admin/product/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024
        * 100)
public class ProductEditController extends HttpServlet {

    private static final String UPLOAD_LOCATION = "/home/quan/DataForProject/demo-hibernate-and-servlet/Product/";

    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = productService.get(Long.parseLong(req.getParameter("idProduct")));
        req.setAttribute("product", product);
        req.getRequestDispatcher("/jsp/view/admin/jsp/edit-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        String productCode = getValue(req.getPart("code"));
        String productName = getValue(req.getPart("productName"));
        String price = getValue(req.getPart("price"));
        String salePrice = getValue(req.getPart("salePrice"));
        String saleDescription = getValue(req.getPart("saleDescription"));
        Category category = categoryService.get(getValue(req.getPart("categoryName")));
        Part filePart = req.getPart("image");

        product.setCode(productCode);

        if (saleDescription == null) {
            saleDescription = "a";
            product.setSaleDescription(saleDescription);
        } else {
            product.setSaleDescription(saleDescription);
        }

        if (productName.isBlank()) {
            message = "Name of product is not empty";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            return;
        } else {
            product.setName(productName);
        }

        if (price.isBlank()) {
            message = "Price is not empty";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            return;
        } else {
            product.setPrice(Integer.parseInt(price));
        }

        if (salePrice.isBlank()) {
            salePrice = "0";
            product.setSalePrice(Integer.parseInt(salePrice));
        } else{
            product.setSalePrice(Integer.parseInt(salePrice));
        }

        if (category == null) {
            message = "Category is not empty";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
            return;
        } else {
            Set<Category> categories = product.getCategories();
            categories.add(category);
            product.setCategories(categories);
        }

        switch (filePart.getContentType()) {
            case "image/png":
                String fileNamePNG = productCode + "-";
                File dirPNG = new File(UPLOAD_LOCATION);
                File uploadPNG = File.createTempFile(fileNamePNG, ".png", dirPNG);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, uploadPNG.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                product.setPicture(uploadPNG.getName());
                resp.sendRedirect(req.getContextPath() + "/admin/product/list");
                break;
            case "image/jpeg":
                String fileNameJPG = productCode + "-";
                File dirJPG = new File(UPLOAD_LOCATION);
                File uploadJPG = File.createTempFile(fileNameJPG, ".jpg", dirJPG);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, uploadJPG.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                product.setPicture(uploadJPG.getName());
                resp.sendRedirect(req.getContextPath() + "/admin/product/list");
                break;
            default:
                message = "File much be .jpg or .png";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/admin/jsp/add-product.jsp").forward(req, resp);
                break;
        }

        try {
            productService.edit(product);
            resp.sendRedirect(req.getContextPath() + "/product/admin/edit");
        } catch (SQLException e) {
            Log.getLog("ProductAddController", e.getMessage(), e);
        }
    }

    private static String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for(int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }
}