package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;
import utils.Random;

@WebServlet(value = "/myaccount")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 100)
public class MyAccountController extends HttpServlet {

    private static final String PHONE_VALID = "^[0-9]{10}$";
    private static final String UPLOAD_LOCATION = "/home/quan/DataForProject/demo-hibernate-and-servlet/User/";

    UserService userService = new UserServiceImpl();
    private String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String fullName = getValue(req.getPart("fullName"));
        Date birthDate = Date.valueOf(getValue(req.getPart("birthDate")));
        String gender = getValue(req.getPart("gender"));
        String address = getValue(req.getPart("address"));
        String phone = getValue(req.getPart("phone"));
        Part filePart = req.getPart("avatar");

        User user = (User) session.getAttribute("user");

        if (!fullName.isBlank()) {
            user.setFullName(fullName);
        } else {
            message = "Enter full name";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        }

        user.setGender(gender);
        user.setBirthDate(birthDate);

        if (address.isBlank()) {
            message = "Enter address";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        } else {
            user.setAddress(address);
        }

        if (phone.isBlank() && phone.matches(PHONE_VALID)) {
            message = "Enter phone number with 10 digit";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            return;
        } else {
            user.setPhone(phone);
        }

        switch (filePart.getContentType()) {
            case "image/png":
                String fileNamePNG = Random.getID("user");
                File dirPNG = new File(UPLOAD_LOCATION);
                File uploadPNG = File.createTempFile(fileNamePNG, ".png", dirPNG);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, uploadPNG.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                user.setAvatar(uploadPNG.getName());
                break;
            case "image/jpeg":
                String fileNameJPG = Random.getID("user");
                File dirJPG = new File(UPLOAD_LOCATION);
                File uploadJPG = File.createTempFile(fileNameJPG, ".jpg", dirJPG);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, uploadJPG.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                user.setAvatar(uploadJPG.getName());
                break;
            default:
                message = "File much be .jpg or .png";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
                break;
        }

        try {
            userService.edit(user);
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/myaccount");
        } catch (SQLException e) {
            message = "Can't edit";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/view/client/jsp/myaccount.jsp").forward(req, resp);
            Log.getLog("MyAccountController", e.getMessage(), e);
        }
    }

    private static String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }
}