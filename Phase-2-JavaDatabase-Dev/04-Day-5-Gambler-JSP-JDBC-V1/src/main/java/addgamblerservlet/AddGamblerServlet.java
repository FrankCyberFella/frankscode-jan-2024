package addgamblerservlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.DataBaseInsertException;
import gambler.Gambler;
import gambler.GamblerDao;
import gambler.JdbcGamblerDao;
import gambler.MemoryGamblerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*************************************************************
 * Servlet to add a Gambler to the Data Source
 * 
 * It is a subclass of HttpServlet
 * 
 * Using the GamblerDao
 *********************************************************************/
@WebServlet("/silence")  // This will handle all /silence path requests from jsp
public class AddGamblerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// Define a reference to the GamblerDao
	private GamblerDao gamblerDataSource;
	
	// Define a constructor to instantiate a GamblerDAO concrete class
	//        and assign it to the reference we defined
	public AddGamblerServlet() throws IOException, SQLException {
		super();  // a subclass must always call it's super class ctor 1st thing
		gamblerDataSource = new JdbcGamblerDao();
	}
	
	/**
	 * This method will handle HTTP POST request sent to the path assigned to servlet
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Add data sent to use from the JSP form to the Gambler Data Source
		
		// Instantiate a new Gambler object to hold that data
		Gambler newGambler = new Gambler();
		
		// Get the data from JSP form HTTP request
		//     and store it in the new Gambler
		// Be sure to use the names assigne to the data in form 
		//    in the request.getParameter() method call
		
		String newName   = request.getParameter("newName");
		String address   = request.getParameter("newAddr");
		String dob       = request.getParameter("newBirthDate");
		String newSalary = request.getParameter("newSalary");
		
		// Use the setters in the new Gambler to populate the object
		// Converting the data as necessary
		
		DateTimeFormatter EurDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		newGambler.setAddress(address);
		newGambler.setBirthDate(LocalDate.parse(dob, EurDateFormat));
		newGambler.setName(newName);
		newGambler.setMonthlySalary(Double.parseDouble(newSalary));		
		
		// Send the new Gambler to the DAO method to add it to the data source
		
		try {
			try {
				gamblerDataSource.addGambler(newGambler);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DataBaseInsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Send the browser back to the HomePage
		response.sendRedirect("./HomePage.jsp");
	} // End of doPost()
	
} // End of Servlet class
