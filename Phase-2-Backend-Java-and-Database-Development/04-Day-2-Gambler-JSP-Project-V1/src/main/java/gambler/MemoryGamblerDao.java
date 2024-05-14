package gambler;

/*****************************************************************************************************************
 * Implement methods for manipulating are returning data from the gambler table
 * At minimum, implement the methods required by the GamblerDao interface
 
    gambler table as defined in the database
  	
 	+----------------+--------------+------+-----+---------+----------------+
    | Field          | Type         | Null | Key | Default | Extra          |
    +----------------+--------------+------+-----+---------+----------------+
    | id             | smallint     | NO   | PRI | NULL    | auto_increment |
    | gambler_name   | char(20)     | NO   |     | NULL    |                |
    | address        | char(20)     | YES  |     | NULL    |                |
    | birth_date     | date         | NO   |     | NULL    |                |
    | monthly_salary | decimal(9,2) | NO   |     | NULL    |                |
    +----------------+--------------+------+-----+---------+----------------+

 *****************************************************************************************************************/

import databaseerror.DataBaseErrorLog;
import errorlog.ErrorLog;
import exceptions.DataBaseDeleteException;
import exceptions.DataBaseInsertException;
import exceptions.DataBaseUpdateException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class MemoryGamblerDao implements GamblerDao {
	
	// define a reference to the DataSource object
	private List<Gambler> theDataBase;
	
	private DataBaseErrorLog theDatabaseErrorLog;

	// constructor for the class which takes the dataSource as a parameter
	// dataSource will be provided when this DAO is instantiated (from application program)
	public MemoryGamblerDao() throws IOException {
		// Instantiate a JdbcTemplate object with the dataSource give and assign it to our reference
		this.theDataBase = new ArrayList();
		setupTheDataBase();
		this.theDatabaseErrorLog = new DataBaseErrorLog("database");
	}


	@Override  // Ask Java to be sure we are correctly providing the methods define in the interface
	public List<Gambler> getAllGamblers() {
        // Create a deep copy of the DataBase to return 
		
		List<Gambler> copyOfDataBase = new ArrayList();
		
		for(Gambler aGambler : theDataBase) {
			copyOfDataBase.add(aGambler);
		}
		return copyOfDataBase ;
	}

	@SuppressWarnings("finally")
	@Override
	// This method receives a gambler object containing the gambler info to be added
	//             returns whether the row was successfully added or not
	public boolean addGambler(Gambler gamblerToAdd) throws DataBaseInsertException {
		
		// Create a deep copy of Gambler to be added
		Gambler aNewGambler = new Gambler(gamblerToAdd);
		
		boolean didAddToDataBaseWork = false;
		
		try {
		// Add new Gambler to the data base
			theDataBase.add(aNewGambler);
			
			didAddToDataBaseWork = true;
		}
		catch (NullPointerException exceptionObject) {
			didAddToDataBaseWork = false;
			
			throw new DataBaseInsertException("Cannot add a null object to data base");
		}
		finally {
			return didAddToDataBaseWork;
		}
	}

	@Override
	public Gambler findGamblerById(long id) {
	
		Gambler theGambler = null;
		
		System.out.println("Looking for Gambler Id: " + id);
		
		for(Gambler currentGambler : theDataBase) {
			if(currentGambler.getId() == id) {
				System.out.println("Found: " + currentGambler);
				theGambler = new Gambler(currentGambler);
				System.out.println("returning: " + theGambler);
		}
		}
	    
	    // Return the object with the values from row in the data set
		return theGambler;
	}

	@Override
	public List<Gambler> findGamblerByName(String searchName) {
		
		// Define an object hold the return value
		List<Gambler> gamblersFound = new ArrayList();	
	
		
		for(Gambler currentGambler : theDataBase) {
			if(currentGambler.getName().contains(searchName)) {
				gamblersFound.add(new Gambler(currentGambler));
			}
		}
	    
	    // Return the object with the values from row in the data bas	
		return gamblersFound;
	}

	@Override	
	// This method receives an gambler object with changed and unchanged data including change
	// We do this so we don't don't have to worry about what has changed
	//    The application is what worries about what has changed
	//    All we do is send and receive data to data base
	// Application logic does not belong in a DOA
	public void update(Gambler gamblerPassed) throws DataBaseUpdateException, DataBaseDeleteException {
	
		Gambler updatedGambler = new Gambler(gamblerPassed);
		
		// Try to find existing Gambler in the data base
		Gambler existingGambler = findGamblerById(gamblerPassed.getId());
		
		// Remove existing Gambler from the data base
		
		try {
			delete(gamblerPassed.getId());
		} 
		catch (DataBaseDeleteException exceptionObject)	{
			theDatabaseErrorLog.writeToDatabaseErrorLog("Cannot find Gambler you are trying to up in the database: Id=" 
                                                       + gamblerPassed.getId()
                                                       +" Name: " + gamblerPassed.getName());
			theDatabaseErrorLog.writeExceptionInfoToDatabaseErrorLog(exceptionObject);
			
			throw new DataBaseUpdateException("Error updating data base; Please review the Data Base Error Log: "
					                         + theDatabaseErrorLog.getErrorLogFileName());
		}
		// Add updated Gambler to the data base
		theDataBase.add(gamblerPassed);	
	}

	@Override
	public void delete(long idToBeDeleted) throws DataBaseDeleteException {
	
		Gambler existingGambler = findGamblerById(idToBeDeleted);
				
		if(existingGambler == null) {

			theDatabaseErrorLog.writeToDatabaseErrorLog("Cannot find Gambler you are trying to delete in the database: Id=" + idToBeDeleted);	

			throw new DataBaseDeleteException("Error deleting from data base; Please review the Data Base Error Log: "
                                            + theDatabaseErrorLog.getErrorLogFileName());
		}
	}		
	
	private void setupTheDataBase() {
		DateTimeFormatter EurDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 
		theDataBase.add(new Gambler(54 , "Neil Bransfield", "Seattle, WA"    , LocalDate.parse("1959-03-11", EurDateFormat) , 5000.00  ));
		theDataBase.add(new Gambler(137, "Phil Donahuge"  , null             , LocalDate.parse("1946-12-29", EurDateFormat) , 3250.25  ));
		theDataBase.add(new Gambler(382, "Stickman Nelson", "Cumberland, MD" , LocalDate.parse("1955-10-21", EurDateFormat) , 12983.75 ));
		theDataBase.add(new Gambler(292, "Betina Chavez"  , "Fresno, CA"     , LocalDate.parse("1963-09-09", EurDateFormat) , 2950.00  ));
		theDataBase.add(new Gambler(12 , "T Judson Smith" , "Los Angeles, CA", LocalDate.parse("1972-05-01", EurDateFormat) , 9839.87  ));
		theDataBase.add(new Gambler(49 , "Dana Imori"     , null             , LocalDate.parse("1989-02-13", EurDateFormat) , 8456.87  ));
		theDataBase.add(new Gambler(191, "Frank Mint"     , "El Paso, TX"    , LocalDate.parse("1993-04-28", EurDateFormat) , 9200.00  ));
		theDataBase.add(new Gambler(572, "Al Mostbroke"   , "Clayton, MO"    , LocalDate.parse("1987-01-18", EurDateFormat) , 28950.00 ));
		theDataBase.add(new Gambler(74 , "Red Squiggledoc", "Java, Indonesia", LocalDate.parse("1996-01-23", EurDateFormat) , 100000.00));
	}

	
} // End of class
