package loginapp;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    public void init(ServletConfig config)throws ServletException
    {
    	super.init(config);
    try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root123");
    if (con == null) 
    {
        throw new ServletException("Failed to establish database connection.");
    }
    }
    catch (ClassNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		String s1=request.getParameter("fname");
		String s2=request.getParameter("lname");
		String s3=request.getParameter("uname");
		String s4=request.getParameter("pword");
		PrintWriter pw=response.getWriter();
		if (con == null) 
		{
	        pw.println("<html><body bgcolor='cyan'><center>");
	        pw.println("<h1>Database connection is not established. Please try again later.</h1>");
	        pw.println("</center></body></html>");
	        return;
	    }
		
		PreparedStatement ps=con.prepareStatement("insert into info values(?,?,?,?)");
		ps.setString(1, s1);
		ps.setString(2, s2);
		ps.setString(3, s3);
		ps.setString(4, s4);
		ps.executeUpdate();
		
		pw.println("<html><body bgcolor=cyan><center>");
		pw.println("<h1>You Have Registerd Successfully</h1>");
		pw.println("<a href=login.html>Login</a>");
		pw.println("</center></body></html>");
		ps.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
