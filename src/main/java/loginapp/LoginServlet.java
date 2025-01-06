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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    public void init(ServletConfig config)
    {
    try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root123");
    } catch (ClassNotFoundException e) {
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
		String s1=request.getParameter("uname");
		String s2=request.getParameter("pword");
		PrintWriter pw=response.getWriter();
		if (con == null) 
		{
	        pw.println("<html><body bgcolor='cyan'><center>");
	        pw.println("<h1>Database connection is not established. Please try again later.</h1>");
	        pw.println("</center></body></html>");
	        return;
	    }
		
		PreparedStatement ps=con.prepareStatement("select * from info where uname=? and pword=?");
		ps.setString(1, s1);
		ps.setString(2, s2);
		ResultSet rs=ps.executeQuery();
		//PrintWriter pw=response.getWriter();
		pw.print("<html><body bgcolor=lightyellow><h1>");
		if(rs.next())
		{
			pw.print("WelCome "+s1);
		}
		else
		{
			pw.println("Invalid UserName/PassWord");
		}
		pw.println("</h1></body></html>");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
