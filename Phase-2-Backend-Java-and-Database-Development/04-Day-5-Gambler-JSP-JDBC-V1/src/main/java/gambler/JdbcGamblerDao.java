package gambler;
/*****************************************************************************************************
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

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcGamblerDao implements GamblerDao {
	
	// define a reference to the JdbcTemplate object we will use to access Spring DAO Framework
	//private JdbcTemplate theDataBase;
	
	//private BasicDataSource theDataBase;
		
	private static Connection theDataBase = null;;

	// constructor for the class which takes the dataSource as a parameter
	// dataSource will be provided when this DAO is instantiated (from application program)
	public JdbcGamblerDao() throws SQLException {
		System.out.println("Native JDBC DAO being setup...");
		
		setupDataSource();
	}


	@Override  // Ask Java to be sure we are correctly providing the methods define in the interface
	public List<Gambler> getAllGamblers() throws SQLException {
		
		// Define a List to hold the POJO created from the table rows received
		List<Gambler> allGamblers = new ArrayList();
		
		// Define statement to be used for queries withOUT placeholders ('?')
		Statement sqlStatement = theDataBase.createStatement();
		
		// Send the SQL string to the database using Native JDBC methods
		// for a select - store the result in an ResultSet object
		//
		// The ResultSet object contains all the rows returned by the select
		//     each row has the columns specified in the select
	
		ResultSet allGamblerRows = sqlStatement.executeQuery("select * from gambler");
	    
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
	public boolean addGambler(Gambler gamblerToAdd) throws SQLException {
		
		Gambler aNewGambler = new Gambler();
		
		// Define a string with the SQL statement to send to the data base
		// Using '?' as placeholder for values stored in program variables
		//       we want to use in the SQL statement
		// Define statement to be used for queries WITH placeholders ('?')
		PreparedStatement addAGambler = 
			theDataBase.prepareStatement("INSERT INTO gambler "
									+ " (gambler_name, address, birth_date, monthly_salary) "
									+ " VALUES (?, ?, ?, ?)");// VALUES contains values to be inserted
		
		// Assign values to the placeholders in the statement
		addAGambler.setString(1, gamblerToAdd.getName());                   // replace 1st '?' with name);
		addAGambler.setString(2, gamblerToAdd.getAddress());                // replace 2nd '?' with address);
		addAGambler.setDate(3, Date.valueOf( gamblerToAdd.getBirthDate())); // replace 3rd '?' with birth date);
		addAGambler.setDouble(4, gamblerToAdd.getMonthlySalary());          // replace 4th '?' with salary);
		
		int numRowsAdded = addAGambler.executeUpdate();
		
		if(numRowsAdded == 1) {
			
		   int idOfRowAdded;
		   // Define statement to be used for queries withOUT placeholders ('?')
		   Statement sqlStatement = theDataBase.createStatement();
		
		   // Retrieve the id assigned to new Gambler in the database
		   ResultSet selectResult = sqlStatement.executeQuery("Select LAST_INSERT_ID()");
		   
		   if (selectResult.next()) {  // If the SELECT returned at leastone row
			  idOfRowAdded = selectResult.getInt(1);
			  System.out.println("Row was added with id: " + idOfRowAdded);
		   }
		}
		if (numRowsAdded == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Gambler findGamblerById(long id) throws SQLException {
	
		// Define the SQL statement to run in a String
		PreparedStatement selectAGamblerByIdSql = theDataBase.prepareStatement("select * from gambler where id = ?");
		
		selectAGamblerByIdSql.setLong(1, id);
		ResultSet aGamblerRow = selectAGamblerByIdSql.executeQuery();
		
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
	public List<Gambler> findGamblerByName(String name) throws SQLException {
		
		// Define an object hold the return value
		List<Gambler> gamblersFound = new ArrayList();	
		
		// Construct the LIKE string with '%' where needed
		String likeString = "%" + name + "%"; // find any row containing the name passed
		
		// Define String with SQL statement to be run with placeholder '?' if needed
		PreparedStatement selectByNameSql = theDataBase.prepareStatement(" Select * from gambler "
																		+" where gambler_name like ?");
		
		selectByNameSql.setString(1, likeString);

		// Send the SQL statement String to data base base with any program variables
		ResultSet rowsFromDataBase = selectByNameSql.executeQuery();
		
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
	public void update(Gambler gamblerPassed) throws SQLException {
	
		PreparedStatement updateGamblerSql = theDataBase.prepareStatement(" update gambler "
																		+ " set gambler_name = ? "
																		+ "            ,address = ?"
																		+ "            ,birth_date = ?"
																		+ "            ,monthly_salary = ?"
																		+ " where id = ?");														
		
		updateGamblerSql.setString(1, gamblerPassed.getName());
		updateGamblerSql.setString(2, gamblerPassed.getAddress());
		updateGamblerSql.setDate  (3, Date.valueOf(gamblerPassed.getBirthDate()));
		updateGamblerSql.setDouble(4, gamblerPassed.getMonthlySalary());
		updateGamblerSql.setLong  (5, gamblerPassed.getId());
		
		int numRowsUpdated = updateGamblerSql.executeUpdate();
	}

	@Override
	public void delete(long id) throws SQLException {
		
		PreparedStatement deleteByIdSql = theDataBase.prepareStatement("delete from gambler where id = ?");
		
		deleteByIdSql.setLong(1, id);
		
		int numRowsDeleted = deleteByIdSql.executeUpdate();
	}		
	
	// Create a gambler POJO from a row in the SqlRowSet
	// Be sure all columns expected by the methods 
	//     were included in the select that created the SqlRowSet
	private Gambler MapRowToGambler(ResultSet aRow) throws SQLException {
		// Define a POJO to be returned
        Gambler aGambler = new Gambler();

        // Use the setters for the POJO to set the values from each column the SqlRowSet
        aGambler.setId(aRow.getInt("id"));
        
        //Note: conversion of data base Date type to Java LocalDate type
        aGambler.setBirthDate(aRow.getDate("birth_date").toLocalDate());
        
        aGambler.setAddress(aRow.getString("address"));
        aGambler.setName(aRow.getString("gambler_name"));
        aGambler.setMonthlySalary(aRow.getDouble("monthly_salary"));

        // Send the completed POJO back to where this method was called
        return aGambler;
    }
	
    private void setupDataSource() {
    /**************************************************************************************************************
     * Instantiate and initialize data source for JDBC data base access
     *************************************************************************************************************/
    if (theDataBase == null) {
            try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    theDataBase = DriverManager.getConnection("jdbc:mysql://localhost:3306/vegasdb", "student", "Java#1Rules");       
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
        }
}
    
public static void close() {
        if (theDataBase != null) {
	        try { theDataBase.close();
	              theDataBase = null;
	        } catch (SQLException e) {
	                e.printStackTrace();
	        }
		}
}

	
} // End of class
