package com.frank.model.casino;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.frank.exceptions.DataBaseDeleteException;
import com.frank.exceptions.DataBaseInsertException;
import com.frank.exceptions.DataBaseUpdateException;

	/* Columns in the casino table
	+-------------+----------+------+-----+---------+-------+
	| Field       | Type     | Null | Key | Default | Extra |
	+-------------+----------+------+-----+---------+-------+
	| casino_name | char(20) | NO   | PRI | NULL    |       |
	| location    | char(15) | NO   |     | NULL    |       |
	| owner       | char(20) | NO   |     | NULL    |       |
	| size        | int      | NO   |     | NULL    |       |
	+-------------+----------+------+-----+---------+-------+
	*/


// Implementation of the DAO interface methods
//   (and any other methods required to access the data base)

public class JdbcCasinoDao implements CasinoDao {
	
	// Define a reference to a JdbcTemplateObject we can use to access the tables in the database
	private JdbcTemplate theMoneyBar;

	// Instantiate a JdbcTemplate object and assign to reference in the constructor
	public JdbcCasinoDao(DataSource aDataSource) {
		this.theMoneyBar = new JdbcTemplate(aDataSource);
	}
	
	// Return all the Casinos in the data base
	@Override
	public List<Casino> getAllCasino() {
		
		// Define a String with SQL to be run
		String allCasinos = "select * from casino"; // Note ; not required in at then end of SQL string
		
		// Send the SQL String to the database manage and store result in an SqlRowSet
		SqlRowSet roseSet = theMoneyBar.queryForRowSet(allCasinos);
		
		// Define the object containing all casinos
		List<Casino> casinoRoyale = new ArrayList();
		
		// "Parse the result" - convert the rows from select into casino objects object
		//                      using a method to retrieve each row, create a casino
		//                         then add the casino object to the list
		
		// Loop through the SqlRowset converting the rows in it, one at a time
		while(roseSet.next()) { // Position at the next row (if there is one)
			casinoRoyale.add(theCasinoMakingMethod(roseSet)); // Convert current row to an object
		}
		
		return casinoRoyale;  // Return the object containing all the casinos
	}
	
	/**************************************************************************
	 * Helper Method(s) - typically private
	 *************************************************************************/
	
	// Convert a row in the results from a Select into a Casino object
	// It's private to prevent others outside this class from being able to use
	// Since its requires an object (SqlRowSet) created in this class it doesn't
	//       make sense to allow other outside the class to use
	
	private Casino theCasinoMakingMethod(SqlRowSet aRow) {
	// Define an object to be returned
		Casino aCasino = new Casino(); // A Casino object initialized to default values
		
	 // Use the setters for the POJO to set the values from each column the SqlRowSet
		String casinoName = aRow.getString("casino_name");
		aCasino.setCasinoName(casinoName);
		
		String location = aRow.getString("location");
		aCasino.setLocation(location);
		
		String owner = aRow.getString("owner");
		aCasino.setOwner(owner);
		
		int size = aRow.getInt("size");
		aCasino.setSize(size);
		
		// Return the object created in this method
		return aCasino;
	}
	
	@Override
//  access return methodName(parameters)	
	public Casino getCasinoByName(String name) {
		// Analyze the Problem:
		//
		// We need to go to the data base and ask for casino whose name we are given
		// The casino name requested is passed to method as a String called name
		
		// Design the solution:
		//
		// Define an object to what is to be returned
		// Define a String with SQL to be run
		// Send the SQL string to the database manager and store the result
		// Convert the result to a Java object
		// Return the Java Object
		
		// Code the design:
		
		// Define object to be returned
		Casino aCasino = null; // We don't have a Casino now, so set it to null
		
		
		// Define a String with SQL to be run
		//    using ? where program variable values will be used
		String findACasinoSQL = " select * from casino"
				              + " where casino_name = ?";
		
		// Send the SQL string to the database manager with program variales for "?
		//      and store the result
		// 
		SqlRowSet theCasinoFromDatabase = theMoneyBar.queryForRowSet(findACasinoSQL, name);
		
		// Convert the result to a Java object
		// Since we know casino_name is the primary key (see chart above)
		//       only one row can come back from the select
		// therefore no loop is necessary to go through the result of the select
		// We can just call the conversion method using the result positioned at the next row
		
		if(theCasinoFromDatabase.next()) {   // if the Casino was found, position the result at the first row
			aCasino = theCasinoMakingMethod(theCasinoFromDatabase); // Send the result to conversion method
		}
		// Return the Java Object
		return aCasino;
	}

	@Override
	public void addCasino(Casino casino) throws DataBaseInsertException {
		
		// Define a String to hold the SQL to be sent to the database manager
		String addACasinoSQL = " insert into casino " 
				              +" (casino_name, location, owner, size) "
				              +" values(?, ?, ?, ?)";		
				
		// Send the SQL String to the database manager with data from Casino object received
		int numberRowsAdded = theMoneyBar.update(addACasinoSQL
												,casino.getCasinoName()
												,casino.getLocation()
												,casino.getOwner()
												,casino.getSize()
												);
		
		// Check to see if a row was added to the data base
		if(numberRowsAdded != 1) {
			throw new DataBaseInsertException("Attempt to add Casino \"" + casino.getCasinoName() + "\"");
		}
		// return to caller
		return;
		
	}

	@Override
	public boolean updateCasino(Casino aCasino) throws DataBaseUpdateException {
		
		// Define return value variable
		boolean updateWorked = false;
		
		// Define String to hold SQL statement to be sent to database manager
		//      with "?" where values in program variables should be used
		//       and where clause to be sure only specific casino is updated
		// Note: Primary Key values should NEVER be updated (per the Relational Data Model)
		String updateSQL = " update casino "
				          +"    set location   = ? "
				          +",       owner      = ? "
				          +",       size       = ? "
				          +" where casino_name = ?";
		
		// Send the SQL String to the database manage and receive number of rows affected
		int numberRowsUpdated = theMoneyBar.update(updateSQL
				                                  ,aCasino.getLocation()
				                                  ,aCasino.getOwner()
				                                  ,aCasino.getSize()
				                                  ,aCasino.getCasinoName()
				                                  );
		// Check to be sure only one row was updated		
		if(numberRowsUpdated == 1) {
			return true;
		}
		else {
			throw new DataBaseUpdateException("Attempt to update Casino \"" + aCasino.getCasinoName() + "\"");
		}
	}

	@Override
	public void trashCasino(String name) throws DataBaseDeleteException {
		
		// Define String to hold SQL statement to send to database manager
		String deleteCasinoSQL = "delete from casino where casino_name = ?";
		
		// Send SQL string to data base manager and receive how many rows were affected
		int numberRowsDeleted = theMoneyBar.update(deleteCasinoSQL, name);
		
		// Check to be sure only one row was deleted
		if(numberRowsDeleted != 1) {
			throw new DataBaseDeleteException("Attempt to delete Casino " + name + "\"");
		}
		
		// return to caller if no exception
		return;
		
		
	}

	
	}



