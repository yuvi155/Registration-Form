package in.backend;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/loginForm")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String pass = req.getParameter("pass1");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myreg", "root", "2539"
			);

			PreparedStatement ps = con.prepareStatement(
				"SELECT * FROM register WHERE email = ? AND password = ?"
			);
			ps.setString(1, email);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				HttpSession session = req.getSession();
				session.setAttribute("name", rs.getString("name"));
				session.setAttribute("email", rs.getString("email"));
				session.setAttribute("gender", rs.getString("gender"));

				
				resp.sendRedirect("dashboard.jsp");
			} else {
				
				req.setAttribute("msg", "Invalid Email or Password!");
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req, resp);
			}

			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "Server Error: " + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
		}
	}
}
