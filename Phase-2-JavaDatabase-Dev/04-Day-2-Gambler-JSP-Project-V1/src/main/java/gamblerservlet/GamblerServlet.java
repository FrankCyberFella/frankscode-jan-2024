package gamblerservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gambler.Gambler;
import gambler.GamblerDao;
import gambler.MemoryGamblerDao;

/**
 * Servlet implementation class GamblerServlet
 */
@WebServlet("/gambler")
public class GamblerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GamblerDao gamblerTable;

	/**
	 * @throws IOException
	 * @see HttpServlet#HttpServlet()
	 */
	public GamblerServlet() throws IOException {
		super();
		gamblerTable = new MemoryGamblerDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter htmlWriter = response.getWriter();
		
		String idRequested          = request.getParameter("id");
		String gamblerNameRequested = request.getParameter("gamblerName");
		String id2Update            = request.getParameter("id2Update");
		String id2Delete            = request.getParameter("id2Delete");
		
		System.out.println("id requested: " + idRequested);
		System.out.println("name requested: " + gamblerNameRequested);
		System.out.println("id to delete: " + id2Update);
		System.out.println("id to update: " + id2Delete);
		
		List<Gambler> theGamblers = new ArrayList();
		
		if(idRequested == null
		&& gamblerNameRequested == null
		&& id2Delete == null
		&& id2Update == null) {
		   theGamblers = gamblerTable.getAllGamblers();
		}
		
		if(idRequested != null 
		&& id2Delete == null
		&& id2Update == null
		&& gamblerNameRequested == null) { 
				theGamblers.add(gamblerTable.findGamblerById(Integer.parseInt(idRequested)));
		}	
		
		if(idRequested == null 
		&& id2Delete == null
		&& id2Update == null
		&& gamblerNameRequested != null) {
		   System.out.println("Looking in database for: " + gamblerNameRequested);
		   theGamblers = gamblerTable.findGamblerByName(gamblerNameRequested);
		}
		

		for(Gambler aGambler : theGamblers) {
			
			htmlWriter.println("<title>Gambler Info</title>");
			htmlWriter.println("<link rel=\"stylesheet\" href=\"./resources/style.css\"></link>");
			
			htmlWriter.println("<h2>Gambler Information for Gambler Id: " + aGambler.getId() + "</h2>");
			
			htmlWriter.println("<div class=aGambler>");
			htmlWriter.println("<p>Name: " + aGambler.getName() + "</p>");
			htmlWriter.println("<p>Address: " + aGambler.getAddress() + "</p>");
			htmlWriter.println("<p>Birth Date: " + aGambler.getBirthDate() + "</p>");
			htmlWriter.println("<p>Monthly Salary: " + aGambler.getMonthlySalary() + "</p>");
			htmlWriter.println("</div>");
			
			htmlWriter.println("<div>");
			htmlWriter.println("<a href=\"./HomePage.jsp\">Return to Home Page</a>");
			htmlWriter.println("</div>");
		}

		response.getWriter().append("Data as of: " + (LocalDateTime.now().toString().replace("T"," "))+ "\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
