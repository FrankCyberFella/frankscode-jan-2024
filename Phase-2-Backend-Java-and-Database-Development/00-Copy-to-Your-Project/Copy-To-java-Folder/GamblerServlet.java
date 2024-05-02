/* Frank Version from Class */
package gamblerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import databaseerror.DataBaseErrorLog;
import exceptions.DataBaseDeleteException;
import exceptions.DataBaseUpdateException;
import gambler.Gambler;
import gambler.GamblerDao;
import gambler.MemoryGamblerDao;

/**
 * Servlet implementation class GamblerServlet
 */
@WebServlet("/gambler")  // This will handle all /gambler path requests from jsp
public class GamblerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GamblerDao gamblerTable;
		
	DataBaseErrorLog databaseLog;

	/**
	 * @throws IOException
	 * @see HttpServlet#HttpServlet()
	 */
	public GamblerServlet() throws IOException {
		super();
		gamblerTable = new MemoryGamblerDao();
		databaseLog = new DataBaseErrorLog("GamblerServlet");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	// This method is run when the form has method="get" for this servlet path
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
// Instantiate a PrintWriter to write HTML into the response
// Note: We get the PrintWriter from the response
//       because the response knows how it wants the HTML written to it		
		PrintWriter htmlWriter = response.getWriter();
		
// Retrieve any query parameters from the URL path
		// these are names in the <input tag of the for in the JSP
		String idRequested          = request.getParameter("id");
		String gamblerNameRequested = request.getParameter("gamblerName");
		String id2Update            = request.getParameter("id2Update");
		String id2Delete            = request.getParameter("id2Delete");
		
		// These statement are for debugging purposes
		// They will display what was in the queryparameters in the server console
		//System.out.println("id requested: " + idRequested);
		//System.out.println("name requested: " + gamblerNameRequested);
		//System.out.println("id to delete: " + id2Update);
		//System.out.println("id to update: " + id2Delete);
		
		// Define the object to be hold any Gamblers retrieved from the data sourse
		// It may contain 0 to any number of gamblers
		List<Gambler> theGamblers = new ArrayList();

// Determine processing based on the query parameter(s) passed to the servlet	
		// if not query parameters are passed - all are null - get all gamblers
		if(idRequested == null
		&& gamblerNameRequested == null
		&& id2Delete == null             
		&& id2Update == null) {                         // get all gamblers from data source
		   theGamblers = gamblerTable.getAllGamblers(); // using the DAO method to do so
		}
		
		// if the id of the Gambler to be displayed was passed as a query parameter (it's not null)
		if(idRequested != null) {    // Use the DAO method to get the Gambler with that id
			// if the Gambler doesn't exist a null Gambler is returned by the DAO and added to the List
		   theGamblers.add(gamblerTable.findGamblerById(Integer.parseInt(idRequested))); // and add it to the list holding the Gamblers
		}	
		
		// if the name of the Gambler to be displayed was passed as a query parameter (it's not null)
		if (gamblerNameRequested != null) {   // Use the DAO method to get the Gambler whose name contains the value
		   // If at least one Gambler doesn't have the name - an empty List is returned
			theGamblers = gamblerTable.findGamblerByName(gamblerNameRequested); // and add it to the list holding the Gamblers
		}
		
// Check to be sure we have found at least one Gambler		
		if(theGamblers.size() == 0) {
			htmlWriter.println("<h1 style=\"color : red;\">No Gamblers found!</h1>");
			
		// Write the HTML to provide a link back to the Home Page
			htmlWriter.println("<div>");
			htmlWriter.println("<a href=\"./HomePage.jsp\">Return to Home Page</a>");
			htmlWriter.println("</div>");			
		}
		
// Now we have all the Gamblers from data source requested any the query parameters in our List of Gamblers		
// We are ready to build the HTML to display each Gambler in the List of Gamblers
		
		for(Gambler aGambler : theGamblers) { // Loop through the list of Gamblers
			
			// Write the HTML for setup the page
			htmlWriter.println("<title>Gambler Info</title>");   // The name to put the browser tab
			htmlWriter.println("<link rel=\"stylesheet\" href=\"./resources/style.css\"></link>"); // Use this stylesheet file
			try {
			// Write the HTML to provide a title on the page - using data from the current Gambler
			htmlWriter.println("<h2>Gambler Information for Gambler Id: " + aGambler.getId() + "</h2>");
			
			// Write the HTML to show all the information for the current Gambler
			htmlWriter.println("<div class=aGambler>");
			htmlWriter.println("<p>Name: " + aGambler.getName() + "</p>");
			htmlWriter.println("<p>Address: " + aGambler.getAddress() + "</p>");
			htmlWriter.println("<p>Birth Date: " + aGambler.getBirthDate() + "</p>");
			htmlWriter.println("<p>Monthly Salary: " + aGambler.getMonthlySalary() + "</p>");
			htmlWriter.println("</div>");
			} // end of try block
			catch(NullPointerException exceptionBlock) { // If there is a NullPointerException - display an error page
				htmlWriter.println("<h1 style=\"color : red;\">Gambler Id: " + idRequested + " not found!</h1>");
			} // End of catch
			
			// Write the HTML to provide a link back to the Home Page
			htmlWriter.println("<div>");
			htmlWriter.println("<a href=\"./HomePage.jsp\">Return to Home Page</a>");
			htmlWriter.println("</div>");
		}  // end of for-loop
		// Write a footer line on the page showing when the data was retrieved
		// Note: Use of the writer in the response (not required)
		response.getWriter().append("Data as of: " + (LocalDateTime.now().toString().replace("T"," "))+ "\n");
	} // End of doGet()

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	} // End of doPost()
	
	
} // End of Servlet class
		
