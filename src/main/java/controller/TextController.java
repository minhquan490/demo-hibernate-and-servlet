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

@WebServlet(value = "/text/*")
public class TextController extends HttpServlet {

    private String textPath;

    @Override
    public void init() throws ServletException {
        this.textPath = "/home/quan/DataForProject/demo-hibernate-and-servlet/Bill/";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getRequestTxt = req.getPathInfo();

        if (getRequestTxt == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File text = new File(textPath, URLDecoder.decode(getRequestTxt, "UTF-8"));

        if (!text.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = getServletContext().getMimeType(text.getName());

        if (contentType == null || !contentType.startsWith("text/plain")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.reset();
        resp.setContentType(contentType);
        resp.setHeader("Content-Length", String.valueOf(text.length()));

        Files.copy(text.toPath(), resp.getOutputStream());
    }
}