import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//retrieve the 4 parameters from register.jsp web form
		String n = request.getParameter("userName");
		String p = request.getParameter("password");
		String e = request.getParameter("email");
		String c = request.getParameter("language");

		try {
		 //attempt connection to database using JDBC
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(
		 "jdbc:mysql://localhost:3306/userdetails", "root", "password");
		 //SQL query
		 PreparedStatement ps = con.prepareStatement("insert into USERDETAILS values(?,?,?,?)");
		 //parse in the data retrieved from the web form request into the ps
		 ps.setString(1, n);
		 ps.setString(2, p);
		 ps.setString(3, e);
		 ps.setString(4, c);
		 
		 int i = ps.executeUpdate();

		 if (i > 0){
			//check if the query had been successfully executed..return successful response
			PrintWriter writer = response.getWriter();
			writer.println("<h1>" + "You have successfully registered an account!" +
			"</h1>" + "<a href=/DevopS/UserServlet/dashboard>" + "Back to Dashboard" + "</a>");

			writer.close();
			}
		}
		// catch & print out exception
		catch (Exception exception) {
		 System.out.println(exception);
		 out.close();
		}
		doGet(request, response);

	}

}
