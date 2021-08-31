package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public boolean sendEmail(String to, String subject, String type, String token) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hminhquan0401@gmail.com", "quan0868550492");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("demo-hibernate-and-servlet"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            switch (type) {
                case "register":
                    String register = "Đăng ký thành công, vui lòng đăng nhập để sử dụng dịch vụ";
                    message.setText(register);
                    break;
                case "forgot":
                    String url = "https://localhost:8080/forgot?a=" + token + '"';
                    String content="<a href='" + url + "'>" + url + "</a>";
                    String text = "Bạn đã gửi yêu cầu reset mật khẩu. Vui lòng nhấp vào link và đặt mật khẩu mới";
                    message.setText(text);
                    message.setText(content);
                    break;
            }
            Transport.send(message);
        } catch (MessagingException e) {
            Log.getLog("SendEmail", e.getMessage(), e);
            return false;
        }
        return true;
    }
}