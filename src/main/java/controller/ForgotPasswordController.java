package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PasswordForgotten;
import model.User;
import service.PasswordForgottenService;
import service.UserService;
import service.impl.PasswordForgottenServiceImpl;
import service.impl.UserServiceImpl;
import utils.Log;
import utils.Random;
import utils.SendEmail;

@WebServlet(value = "/user/forgot")
public class ForgotPasswordController extends HttpServlet {

    UserService userService = new UserServiceImpl();
    PasswordForgottenService forgottenService = new PasswordForgottenServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if (token == null) {
            req.setAttribute("validate", 0);
            req.getRequestDispatcher("/jsp/view/client/jsp/forgot-password.jsp").forward(req, resp);
        } else {
            req.setAttribute("validate", 1);
            req.getRequestDispatcher("/jsp/view/client/jsp/forgot-password.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int validate = Integer.parseInt(req.getParameter("validate"));

        if (validate == 0) {
            String email = req.getParameter("email");

            if (userService.checkEmailExist(email)) {
                String token = Random.getID("token");

                PasswordForgotten forgotten = new PasswordForgotten();
                User user = userService.get(email);

                forgotten.setToken(token);
                forgotten.setUser(user);;

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateNow = LocalDate.now();
                forgotten.setDateCreated(Date.valueOf(dtf.format(dateNow)));

                LocalTime timeNow = LocalTime.now(ZoneId.systemDefault());
                Time time = Time.valueOf(timeNow);
                forgotten.setTimeCreated(time);

                SendEmail sendEmail = new SendEmail();
                sendEmail.sendEmail(email, "Demo Hibernate And Servlet", "forgot", token);
            } else {
                String message = "Email không tồn tại";
                req.setAttribute("message", message);
                req.getRequestDispatcher("/jsp/view/client/jsp/forgot-password.jsp").forward(req, resp);
            }
            return;
        }

        if (validate == 1) {
            PasswordForgotten forgotten = forgottenService.get(req.getParameter("token"));

            if (forgotten == null) {
                req.getRequestDispatcher("/jsp/view/client/jsp/forgot-password.jsp").forward(req, resp);
                return;
            }

            LocalDate dateCreated = LocalDate.parse(forgotten.getDateCreated().toString());
            LocalTime timeCreated = LocalTime.parse(forgotten.getTimeCreated().toString());
            Period period = dateCreated.until(LocalDate.now(ZoneId.systemDefault()));
            if (LocalTime.now(ZoneId.systemDefault()).toSecondOfDay() - timeCreated.toSecondOfDay() < 0 && period.getDays() == 1 || period.getDays() < 1) {
                String password = req.getParameter("password");
                String rePassword = req.getParameter("re-password");

                User user = forgotten.getUser();

                if (password.equals(rePassword)) {
                    user.setPassword(password);
                    try {
                        userService.edit(user);
                        req.setAttribute("message", "Reset mật khẩu thành công");
                        req.getRequestDispatcher("/jsp/view/client/jsp/forgot-password.jsp").forward(req, resp);
                    } catch (SQLException e) {
                        Log.getLog("ForgotPasswordController", e.getMessage(), e);
                    }
                } else {
                    req.setAttribute("message", "Mật khẩu và nhập lại mật khẩu không khớp");
                    req.getRequestDispatcher("/jsp/view/client/jsp/forgot-password.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "Link đã hết hạn");
                req.getRequestDispatcher("/jsp/view/client/jsp/forgot-password.jsp").forward(req, resp);
            }
        }
    }
}