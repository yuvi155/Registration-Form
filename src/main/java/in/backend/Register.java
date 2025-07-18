package in.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regForm")
public class Register extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String myname =req.getParameter("name1");
		String myemail =req.getParameter("email");
		String mypass =req.getParameter("pass1");
		String mygender =req.getParameter("gender1");
		String city =req.getParameter("city1");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myreg","root","2539");
			PreparedStatement ps =con.prepareStatement("insert into register values(?,?,?,?,?,?)");
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypass);
			ps.setString(4, mygender);
			ps.setString(5, city);
			ps.setString(6, null); 
			int count =ps.executeUpdate();
			
			if(count>0) {
				
				resp.setContentType("text/html");
				out.print("<h3 style='color:green'>User Registered Sucessfully </h3>");
				RequestDispatcher rd =req.getRequestDispatcher("/login.jsp");
				rd.include(req, resp);
				
			}
			else {
				resp.setContentType("text/html");
				out.print("<h3 style='color:red'>User not Registered due to some error! y </h3>");
				RequestDispatcher rd =req.getRequestDispatcher("/register.jsp");
				rd.include(req, resp);
				
				
			}
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("<h3 style='color:red'>Exception Occured :  "+e.getMessage()+"</h3>");
			RequestDispatcher rd =req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
			
			
		}
	}
	
	

}
