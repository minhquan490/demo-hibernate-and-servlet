package controller.admin;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/log/*")
public class ReadLogController extends HttpServlet {
    private String logPath;

    @Override
    public void init() throws ServletException {
        this.logPath = "/home/quan/Log-for-demo-hibernate-and-servlet/";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File log = new File(logPath, URLDecoder.decode("Error.log", "UTF-8"));

        String contentType = getServletContext().getMimeType(log.getName());

        resp.reset();
        resp.setContentType(contentType);
        resp.setHeader("Content-Length", String.valueOf(log.length()));

        Files.copy(log.toPath(), resp.getOutputStream());
    }
}