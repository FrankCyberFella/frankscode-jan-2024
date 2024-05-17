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
	
	// In this DAO we are using Native JDBC access rather than Spring JdbcTemplate access
	//
	//************************************************************************************************
	// 1. There is no BasicDataSourec object defined, initialized and used
	// 2. Thise is no JdbcTemplate object defined, initialized and used
	
	// define a reference to the JdbcTemplate object we will use to access Spring DAO Framework
	//private JdbcTemplate theDataBase;
	
	//private BasicDataSource theDataBase;
	//************************************************************************************************	
	
	// Instead of a BasicDataSource we define, instantiate and use a Connection object for the data source
	private static Connection theDataBase = null;;

	// constructor for the class which takes the dataSource as a parameter
	// dataSource will be provided when this DAO is instantiated (from application program)
	public JdbcGamblerDao() throws SQLException {
		System.out.println("Native JDBC DAO being setup...");  // Notify we are using Native Jdbc
		// Call method to instantiate the connection and assign it to the reference defined above
		setupDataSource();
	}


	@Override  // Ask Java to be sure we are correctly providing the methods define in the interface
	public List<Gambler> getAllGamblers() throws SQLException {
		
		// Define a List to hold the POJO created from the table rows received
		List<Gambler> allGamblers = new ArrayList();
		
		// Define Statement object to be used for queries withOUT placeholders ('?')
		Statement sqlStatement = theDataBase.createStatement();
		
		// Send the SQL string to the database using Native JDBC methods
		// for a select - store the result in an ResultSet object
		//
		// The ResultSet object contains all the rows returned by the select
		//     each row has the columns specified in the select
	
		// Use the Statement object to send the SQL to be run to the data base manager
		// a SELECT returns a ResultSet object (NOT an SqlResultSet like in Spring Jdbc)
		// use the executeQuery() method to run a SELECT with no placeholders ('?')
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
		// Define PreparedStatement object to be used for queries WITH placeholders ('?')
		// Use the prepareStatement() method sending it the SQL to be run with placeholders ('?')
		PreparedStatement addAGambler = 
			theDataBase.prepareStatement("INSERT INTO gambler "
									+ " (gambler_name, address, birth_date, monthly_salary) "
									+ " VALUES (?, ?, ?, ?)");// VALUES contains values to be inserted
		
		// Assign values to the placeholders in the PreparedStatement object using setxxxxxx()
		//  Be sure you use the correct setxxxxx method for the data type in the table
		//         .setString(placeholder #, value-for-placeholder)
		// Note: copnverts of Java LocalDate for BirthDate to SQL Date object
		addAGambler.setString(1, gamblerToAdd.getName());                   // replace 1st '?' with name);
		addAGambler.setString(2, gamblerToAdd.getAddress());                // replace 2nd '?' with address);
		addAGambler.setDate(3, Date.valueOf( gamblerToAdd.getBirthDate())); // replace 3rd '?' with birth date);
		addAGambler.setDouble(4, gamblerToAdd.getMonthlySalary());          // replace 4th '?' with salary);
		
		// Use the PreparedStatememnt executeUpdate() method to send the SQL to the data base
		// executeUpdate() returns the numbers affected in the data base
		int numRowsAdded = addAGambler.executeUpdate();
		
		if(numRowsAdded == 1) {
		
		   // Since the data base manager assigns the id automatically vis AUTO_INCREMENT
		   //       we don't know what value was assigned to the new Gambler
		   // If we want know what it was (maybe to add a row to a dependent foreign key...)
		   // We can use the LAST_INSERT_ID() function to retreive it 
			
		   // Define Statement object to be used for queries withOUT placeholders ('?')
		   Statement sqlStatement = theDataBase.createStatement();
		
		   // Retrieve the id assigned to new Gambler in the database
		   // executeQuery() is used because our SQL statement has no placeholdered ('?")
		   ResultSet selectResult = sqlStatement.executeQuery("Select LAST_INSERT_ID()");
		   
		   int idOfRowAdded = 0;
		   
		   if (selectResult.next()) {  // If the SELECT returned at least one row
			  idOfRowAdded = selectResult.getInt(1); // Get the integer from the row (Last Insert Id)
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
	
		// Define the PreparedStatement with the SQL to run in a String
		PreparedStatement selectAGamblerByIdSql = theDataBase.prepareStatement("select * from gambler where id = ?");
		
		// Assign values to placeholders in the PreparedStatement
		selectAGamblerByIdSql.setLong(1, id);
		
		// Send the PreparedStatement to data base manger using executeQuery()
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
		
		// Define PreparedStatement statement to be run with placeholder '?' if needed
		PreparedStatement selectByNameSql = theDataBase.prepareStatement(" Select * from gambler "
																		+" where gambler_name like ?");
		
		// Set the values for placeholders in the PreparedStatement
		selectByNameSql.setString(1, likeString);

		// Send the PreparedStatement to data base base using executeQuery()
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
	
		// Define a PreparedStatement object with SQL and placeholded=rs
		PreparedStatement updateGamblerSql = theDataBase.prepareStatement(" update gambler "
																		+ " set gambler_name = ? "
																		+ "            ,address = ?"
																		+ "            ,birth_date = ?"
																		+ "            ,monthly_salary = ?"
																		+ " where id = ?");														
		
		// Assign values to the placeholders in the PreparedStatement
		updateGamblerSql.setString(1, gamblerPassed.getName());
		updateGamblerSql.setString(2, gamblerPassed.getAddress());
		updateGamblerSql.setDate  (3, Date.valueOf(gamblerPassed.getBirthDate()));
		updateGamblerSql.setDouble(4, gamblerPassed.getMonthlySalary());
		updateGamblerSql.setLong  (5, gamblerPassed.getId());
		
		// Send the PrepareedStatement to the data base manager using executeUpdate()
		int numRowsUpdated = updateGamblerSql.executeUpdate();
	}

	@Override
	public void delete(long id) throws SQLException {
		
		// Define PreparedStatement object with SQL and placeholders
		PreparedStatement deleteByIdSql = theDataBase.prepareStatement("delete from gambler where id = ?");
		
		// Assign values to the placeholders in the PreparedStatement
		deleteByIdSql.setLong(1, id);
		
		// Send the PreparedStatement to the data base manager using executeUpdate()
		int numRowsDeleted = deleteByIdSql.executeUpdate();
	}		
	
	// Create a gambler POJO from a row in the ResultSet object
	// Be sure all columns expected by the methods 
	//     were included in the select that created the ResultSet
	// This method receives a ResultSet object (NOT an SqlRowSet object like SpringJdbc)
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
     * Instantiate and initialize data source for NativeJDBC data base access
     *************************************************************************************************************/
	    // If it's not yet defined..
		if (theDataBase == null) {
	        try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                // The same connection string is used for both native JDBC access as JdbcTempate access
	                // Connect to the database manager and database
	                theDataBase = DriverManager.getConnection("jdbc:mysql://localhost:3306/vegasdb", "student", "Java#1Rules");       
	            } catch (Exception ex) {
	                    ex.printStackTrace();
	            }
		}
    } // End of setupDataSOurce method
    // Used to disconnect from the data base - may be used by server or application
	public static void close() {
	        if (theDataBase != null) {
		        try { theDataBase.close();
		              theDataBase = null;
		        } catch (SQLException e) {
		                e.printStackTrace();
		        }
			}
	} // End of close() method

	
} // End of class
