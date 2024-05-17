package simplilearn_jan24;

import java.io.IOException;

import frankexample.exceptions.DataBaseDeleteException;
import frankexample.exceptions.DataBaseInsertException;
import frankexample.exceptions.DataBaseUpdateException;

public class DemoStartProgram {
	
	/************************************************************************
	 * main() is the starting point for all Java applications
	 * 
	 * In this application structure:
	 * 
	 *   1. The actual application program is in a different class than main()
	 *   2. What main() does:
	 *      a. Instantiate an object representing the application program
	 *      b. Use that object to call the method that runs the application
	 * @throws DataBaseUpdateException 
	 * @throws DataBaseDeleteException 
	 * @throws DataBaseInsertException 
	 * 
	 *************************************************************************/
	
	public static void main(String[] args) throws DataBaseDeleteException, DataBaseUpdateException, DataBaseInsertException, IOException {
		
		// Instantiate an object representing the application program
		SampleJDBCApplication theSampleApp = new SampleJDBCApplication();
		
		// Use that object to call the method that runs the application
		theSampleApp.run();				
	}

}
