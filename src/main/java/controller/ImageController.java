package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images/*")
public class ImageController extends HttpServlet {

    private String imagePath;

    @Override
    public void init() throws ServletException {
        this.imagePath = "/home/quan/DataForProject/demo-hibernate-and-servlet/";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getRequestImage = req.getPathInfo();

        if (getRequestImage == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File image = new File(imagePath, URLDecoder.decode(getRequestImage, "UTF-8"));

        if (!image.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = getServletContext().getMimeType(image.getName());

        if (contentType == null || !contentType.startsWith("image")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.reset();
        resp.setContentType(contentType);
        resp.setHeader("Content-Length", String.valueOf(image.length()));

        Files.copy(image.toPath(), resp.getOutputStream());
    }
}