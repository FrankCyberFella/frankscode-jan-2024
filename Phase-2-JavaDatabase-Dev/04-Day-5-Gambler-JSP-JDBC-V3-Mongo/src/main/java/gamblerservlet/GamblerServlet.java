/* Frank Version from Class */
package gamblerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import databaseerror.DataBaseErrorLog;
import exceptions.DataBaseUpdateException;
import gambler.Gambler;
import gambler.GamblerDao;

import gambler.MongoGamblerDao;

/**
 * Servlet implementation class GamblerServlet
 */
@WebServlet("/gambler")  // This will handle all /gambler path requests from jsp
public class GamblerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private GamblerDao gamblerTable;
		
	DataBaseErrorLog databaseLog;
	
	// Reference to writer in response to be used to add html top be returned
	// Need to be assigned by each method (doGet(), doPost(), etc) for response it receives
	// It is defines as an instance variable so any helper methods used have access
	PrintWriter htmlWriter; 

	/**
	 * @throws IOException
	 * @throws SQLException 
	 * @see HttpServlet#HttpServlet()
	 */
	public GamblerServlet() throws IOException, SQLException {
		super();

		gamblerTable = new MongoGamblerDao();
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
		
// Assign htmlWriter reference to writer provided by response
// Note: We get the PrintWriter from the response
//       because the response knows how it wants the HTML written to it		
		htmlWriter = response.getWriter();
		
// Retrieve any query parameters from the URL path
		// these are names in the <input tag of the for in the JSP
		String idRequested          = request.getParameter("id");
		String gamblerNameRequested = request.getParameter("name");
		String id2Update            = request.getParameter("id2Update");
		String id2Delete            = request.getParameter("id2Delete");
		
		// These statement are for debugging purposes
		// They will display what was in the queryparameters in the server console
		System.out.println("-".repeat(60) + "query parameters received in gamblerServlet:");
		System.out.println("id requested: " + idRequested);
		System.out.println("name requested: " + gamblerNameRequested);
		System.out.println("id to delete: " + id2Update);
		System.out.println("id to update: " + id2Delete);
		
		// Define the object to be hold any Gamblers retrieved from the data sourse
		// It may contain 0 to any number of gamblers
		List<Gambler> theGamblers = new ArrayList();

// Determine processing based on the query parameter(s) passed to the servlet	
		// if not query parameters are passed - all are null - get all gamblers
		if(idRequested == null
		&& gamblerNameRequested == null
		&& id2Delete == null             
		&& id2Update == null) {                         // get all gamblers from data source
		
			try {
				theGamblers = gamblerTable.getAllGamblers();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(id2Delete != null) {
				try {
					gamblerTable.delete(Long.parseLong(id2Delete));
				} catch (Exception  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				finally {			
					response.sendRedirect("./HomePage.jsp");
				}
		
		}
		// if the id of the Gambler to be displayed was passed as a query parameter (it's not null)
		if(idRequested != null) {    // Use the DAO method to get the Gambler with that id
			// if the Gambler doesn't exist a null Gambler is returned by the DAO and added to the List
		   try {
			theGamblers.add(gamblerTable.findGamblerById(Integer.parseInt(idRequested)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // and add it to the list holding the Gamblers
		}	
		
		// if the name of the Gambler to be displayed was passed as a query parameter (it's not null)
		if (gamblerNameRequested != null) {   // Use the DAO method to get the Gambler whose name contains the value
		   // If at least one Gambler doesn't have the name - an empty List is returned
		
		   try {
			theGamblers = gamblerTable.findGamblerByName(gamblerNameRequested);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			htmlWriter.println("<h2>Gambler Information for Gambler Id: " + aGambler.getGamblerId() + "</h2>");
			
			// Write the HTML to show all the information for the current Gambler
			htmlWriter.println("<div class=aGambler>");
			htmlWriter.println("<p>Name: " + aGambler.getName() + "</p>");
			htmlWriter.println("<p>Address: " + aGambler.getAddress() + "</p>");
			htmlWriter.println("<p>Birth Date: " + aGambler.getBirthDate() + "</p>");
			htmlWriter.println("<p>Monthly Salary: " + aGambler.getMonthlySalary() + "</p>");
			htmlWriter.println("</div>");
			} // end of try block
			catch(NullPointerException exceptionBlock) { // If there is a NullPointerException - display an error page
				htmlWriter.println("<link rel=\"stylesheet\" href=\"./resources/style.css\"></link>"); // Use this stylesheet file
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
		
		// Reference to writer in response to be used to add html top be returned
		// Need to be assigned by each method (doGet(), doPost(), etc) for response it receives
		// It is defines as an instance variable so any helper methods used have access	
		htmlWriter = response.getWriter();
		
		// Retrieve any query parameters from the URL path
		
		String id2Update  = request.getParameter("id2Update");
		String action	  = request.getParameter("action");		
		
		// Define PrintWriter to write HTML to response object
		htmlWriter = response.getWriter();
		
		// Debugging code
		//System.out.println("Updating Gambler Id: " + id2Update);
		//System.out.println("Action requested: "    + action);
		
		// Go get the Gambler to be updated from the data source
		Gambler aGambler = null;
		try {
			aGambler = gamblerTable.findGamblerById(Long.parseLong(id2Update));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// If we don't find the Gambler in the data source
		// Put out a message with a link to home page
		if(aGambler == null) {
			             
			htmlWriter.println("<h1 style=\"color:red;\">Gambler id: " + id2Update + " not found!</h1>");
			
			htmlWriter.println("<div>");
			htmlWriter.println("<a href=\"./HomePage.jsp\">Return to Home Page</a>");
			htmlWriter.println("</div>");
	
		}
		// "action" may contain multiple values 
		// So process based on the values
		switch (action) {
			case "update" :{ // comes from the home page jsp
				displayGamblerAndGetNewData(aGambler);
				break;
			}
			case "ready2Update": { // comes from the displayGamblerAndGetNewData() form submit 
	
				try {
					performUpdate(request, Long.parseLong(id2Update));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
				response.sendRedirect("./HomePage.jsp");
				break;
			}
		} // End of switch
	} // End of doPost()
	private void displayGamblerAndGetNewData(Gambler aGambler) {
		
	htmlWriter.println("<title>Gambler Info</title>");
	htmlWriter.println("<link rel=\"stylesheet\" href=\"./resources/style.css\"></link>");
	
	//                  <form action=\"./gambler?action=ready2update\"                        method=\"post\">
	htmlWriter.println("<form action=\"./gambler?action=ready2Update&id2Update=" + aGambler.getGamblerId() + "\" method=\"post\">");
	htmlWriter.println("<h2>Gambler Information for Gambler Id: " + aGambler.getGamblerId() + "</h2>");	
	htmlWriter.println("<h4>Please enter new data only for the fields you'd like to change. Thank you!<h4>");
	htmlWriter.println("<div class=aGambler>");

	htmlWriter.println("<p>Name: " + aGambler.getName()                    + "<input type=\"text\" size=25 name=\"newName\">      </p>");
	htmlWriter.println("<p>Address: " + aGambler.getAddress()              + "<input type=\"text\" size=25 name=\"newAddr\">      </p>");
	htmlWriter.println("<p>Birth Date: " + aGambler.getBirthDate()         + "<input type=\"text\" size=10 name=\"newBirthDate\"> </p>");
	htmlWriter.println("<p>Monthly Salary: " + aGambler.getMonthlySalary() + "<input type=\"text\" size=9  name=\"newSalary\">    </p>");
	htmlWriter.println("</div>");
		
	htmlWriter.println("<input type=\"submit\" value=\"Submit Changes\">");
	htmlWriter.println("</form>");
	
	htmlWriter.println("<div>");
	htmlWriter.println("<a href=\"./HomePage.jsp\">Return to Home Page</a>");
	htmlWriter.println("</div>");
	} // End of displayGambler and getNewData()
	
	private void performUpdate(HttpServletRequest request, long id2Update) throws Exception {
		
		Gambler gambler2Update = null;
		
		gambler2Update = gamblerTable.findGamblerById(id2Update);
				
		String newName = request.getParameter("newName");
		String newAddr = request.getParameter("newAddr");
		String newBday = request.getParameter("newBirthDate");
		String newSal  = request.getParameter("newSalary");
		
		/* These are present for debugging purposes to verify parameters received
		System.out.println("newName: " + newName);
		System.out.println("newAddr: " +newAddr);
		System.out.println("newBDay: " +newBday);
		System.out.println("newSal : " +newSal);
		*/
		
		if(!newName.equals("")) {
			gambler2Update.setName(newName);
		}
		
		if(!newAddr.equals("")) {
			gambler2Update.setAddress(newAddr);
		}
		
		if(!newSal.equals("")) {
			gambler2Update.setMonthlySalary(Double.parseDouble(newSal));
		}
		
		if(!newBday.equals("")) {
			gambler2Update.setBirthDate(newBday);
		}
		
		
		gamblerTable.update(gambler2Update);
		
		
	}
	// End of performUpdate()

	
} // End of Servlet class
		
