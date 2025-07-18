package in.backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String token = req.getParameter("token");
        String newPassword = req.getParameter("newPassword");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/myreg", "root", "2539");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE register SET password=?, reset_token=NULL WHERE reset_token=?");
            ps.setString(1, newPassword);
            ps.setString(2, token);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                req.setAttribute("msg", "Password successfully updated!");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("msg", "Invalid or expired reset link.");
                RequestDispatcher rd = req.getRequestDispatcher("resetpassword.jsp?token=" + token);
                rd.forward(req, resp);
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Something went wrong.");
            RequestDispatcher rd = req.getRequestDispatcher("resetpassword.jsp?token=" + token);
            rd.forward(req, resp);
        }
    }
}
