package com.frank.model.gambler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import frankexample.databaseerror.DataBaseErrorLog;
import frankexample.errorlog.ErrorLog;

public class JdbcGamblerDao implements GamblerDao {
	
	// define a reference to the JdbcTemplate object we will use to access Spring DAO Framework
	private JdbcTemplate theDataBase;

	// Define a reference to a DatabaseErrorLog object
	private DataBaseErrorLog theDatabaseErrorLog;

	// constructor for the class which takes the dataSource as a parameter
	// dataSource will be provided when this DAO is instantiated (from application program)
	public JdbcGamblerDao(DataSource dataSource) throws IOException {
		// Instantiate a JdbcTemplate object with the dataSource give and assign it to our reference
		this.theDataBase = new JdbcTemplate(dataSource);
		// Instantiate a DataBaseErrorLog object and assign it to the reference
		this.theDatabaseErrorLog = new DataBaseErrorLog("database");  // use "database" as the error log prefix
	}


	@Override  // Ask Java to be sure we are correctly providing the methods define in the interface
	public List<Gambler> getAllGamblers() {

		theDatabaseErrorLog.writeToDatabaseErrorLog("Test Error Log Message");

		// Define a List to hold teh POJO created from the table rows received
		List<Gambler> allGamblers = new ArrayList();
		
		// Define a String with the SQL to access the data base
		String selectAllGamblersSql = "select * from gambler";
		
		// Send the SQL string to the database using jdbcTemplate methods
		// for a select - store the result in an SqlRowSet object
		//
		// The SqlRowSet object contains all the rows returned by the select
		//     each row has the columns specified in the select
		// Note: use of .queryForRowSet() method to run a SELECT SQL statement
	    SqlRowSet allGamblerRows = theDataBase.queryForRowSet(selectAllGamblersSql);
	    
	    // Convert each row returned to a Java POJO for the row
	    // Loop through the SqlRowSet positioning at the next row with .next()
        while(allGamblerRows.next()) {
            allGamblers.add(MapRowToGambler(allGamblerRows)); // Call method to create POJO
        }                                                     // and add the POJO to out List
		
        // Return the list of POJOs created from rows returned from the select
		return allGamblers;
	}

	@Override
	// This method receives a gambler object containing the gambler info to be added
	//             returns whether the row was successfully added or not
	public boolean addGambler(Gambler gamblerToAdd) {
		
		Gambler aNewGambler = new Gambler();
		
		// Define a string with the SQL statement to send to the data base
		// Using '?' as placeholder for values stored in program variables
		//       we want to use in the SQL statement
		String addAGamblerSql = "INSERT INTO gambler "
				              + " (gambler_name, address, birth_date, monthly_salary) "
				              + " VALUES (?, ?, ?, ?)";// VALUES contains values to be inserted
		
		int numRowsAdded = 0;
		
		// We enclose the call to the data base manager in a try block
		//    in case an exception is thrown.  
		// We want to continue if there is an exception thrown rather than be terminated
		
		// Send the SQL statement to the data base manage with the program variable values
		//      that replace the '?' in the SQL statement
		// Note: use of .update() method to run a non-SELECT SQL statement
		try {	        
			numRowsAdded = theDataBase.update(addAGamblerSql
					                         ,gamblerToAdd.getName()          // replace 1st '?' with name
					                         ,gamblerToAdd.getAddress()       // replace 2nd '?' with address
					                         ,gamblerToAdd.getBirthDate()     // replace 3rd '?' with birthdate
					                         ,gamblerToAdd.getMonthlySalary() // replace 4th '?' with salary
				                             );
		}
		// The only exception JdbcTemplates throw is a DataAccessException
		catch (DataAccessException exceptionInfo) {
			System.out.println("!!!!! Error adding new gambler to Database !!!!");
		 }
		int idOfRowAdded;
		SqlRowSet selectResult = theDataBase.queryForRowSet("Select LAST_INSERT_ID()");
		if (selectResult.next()) {
			idOfRowAdded = selectResult.getInt(1);
			System.out.println("Row was added with id: " + idOfRowAdded);
		}

		if (numRowsAdded == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Gambler findGamblerById(long id) {
	
		// Define the SQL statement to run in a String
		String selectAGamblerByIdSql = "select * from gambler where id = ?";
		
		// Send the SQL statement to the data base manager 
		//  providing any program variables to replace '?' in the SQL statemen
	    SqlRowSet aGamblerRow = theDataBase.queryForRowSet(selectAGamblerByIdSql, id);

	    // Define an object to be return
	    Gambler aGambler = null;
	   
	    // If at least one row was returned from the SELECT SQL statment
	    // Convert it to a POJO
	    if(aGamblerRow.next()) {
          aGambler = MapRowToGambler(aGamblerRow);
	    }
	    
	    // Return the object with the values from row in the data set
		return aGambler;
	}

	@Override
	public List<Gambler> findGamblerByName(String name) {
		
		// Define an object hold the return value
		List<Gambler> gamblersFound = new ArrayList();	
		
		// Construct the LIKE string with '%' where needed
		String likeString = "%" + name + "%"; // find any row containing the name passed
		
		// Define String with SQL statement to be run with placeholded '?' if needed
		String selectByNameSql = " Select * from gambler "
				                +" where gambler_name like ?";
				               // + "Where gambler_name = ?";
		
		// Send the SQL statement String to data base base with any program variables
		SqlRowSet rowsFromDataBase = theDataBase.queryForRowSet(selectByNameSql, likeString);
		
		// Convert all rows received from data base to POJOs and add to List to returnec
		while(rowsFromDataBase.next()) {
	          gamblersFound.add(MapRowToGambler(rowsFromDataBase));
	    }
		  
		// Return the List of POJOs with rows found in database		                      
		return gamblersFound;
	}

	@Override
	
	// This method receives an gambler object with changed and unchanged data including change
	// We do this so we don't don't have to worry about what has changed
	//    The application is what worries about what has changed
	//    All we do is send and receive data to data base
	// Application logic does not belong in a DOA
	public void update(Gambler gamblerPassed) {
	
		String updateGamblerSql = " update gambler "
				                + " set gambler_name = ? "
				                + "            ,address = ?"
				                + "            ,birth_date = ?"
				                + "            ,monthly_salary = ?"
				                + " where id = ?";
		
		try {		
			theDataBase.update(updateGamblerSql, gamblerPassed.getName()
					                           , gamblerPassed.getAddress()
					                           , gamblerPassed.getBirthDate()
					                           , gamblerPassed.getMonthlySalary()
					                           , gamblerPassed.getId()
					
							  );
		}
		catch (DataAccessException exceptionInfo) {
			System.out.println("!!!!! Error adding new gambler " + gamblerPassed.getName() + " to Database !!!!");	 }
			
	}

	@Override
	public void delete(long id) {
		
		String deleteByIdSql = "delete from gambler where id = ?";
		
		try {
			theDataBase.update(deleteByIdSql, id);
		}
		catch (DataAccessException exceptionInfo) {
			System.out.println("!!!!! Error deleting gambler Id " + id + " from to Database !!!!");
		 }	
	}		
	
	// Create a gambler POJO from a row in the SqlRowSet
	// Be sure all columns expected by the methods 
	//     were included in the select that created the SqlRowSet
	//     or you will get an Invalid Column Name error
	private Gambler MapRowToGambler(SqlRowSet aRow) {
		// Define a POJO to be returned
        Gambler aGambler = new Gambler();
		try {
			// Use the setters for the POJO to set the values from each column the SqlRowSet
			aGambler.setId(aRow.getInt("id"));

			//Note: conversion of data base Date type to Java LocalDate type
			aGambler.setBirthDate(aRow.getDate("birth_date").toLocalDate());

			aGambler.setAddress(aRow.getString("address"));
			aGambler.setName(aRow.getString("gambler_name"));
			aGambler.setMonthlySalary(aRow.getDouble("monthly_salary"));
		}
		// Spring JDBC rolls all SQL Exceptions into the DataAccessException
		catch (DataAccessException exceptionInfo) {
			// Notify the user there was an error
			System.out.println("!!!!! Data base error !!!!!" );
			// Log the details of the error on the error log - pass Exception object to the logger
			theDatabaseErrorLog.writeExceptionInfoToDatabaseErrorLog(exceptionInfo);
		}
        // Send the completed POJO back to where this method was called
        return aGambler;
    }
	
} // End of class
