package in.backend;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ForgetPass")
public class ForgetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String token = UUID.randomUUID().toString();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/myreg", "root", "2539");

            PreparedStatement ps = con.prepareStatement("UPDATE register SET reset_token=? WHERE email=?");
            ps.setString(1, token);
            ps.setString(2, email);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                String resetLink = "http://192.168.1.20/Registration/resetpass.jsp?token=" + token;
                sendResetEmail(email, resetLink);
                req.setAttribute("msg", "Reset link sent to your email.");
            } else {
                req.setAttribute("msg", "Email not found!");
            }
            RequestDispatcher rd = req.getRequestDispatcher("ForgetPass.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendResetEmail(String recipientEmail, String resetLink) {
        String fromEmail = "yuvrajpandey155@gmail.com"; // Your Gmail
        String password = "shuq hvlk qpfi dwcl"; // Gmail App Password
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Password Reset Link");
            message.setText("Click this link to reset your password: " + resetLink);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
